package com.china.bosh.mylibrary.retrofit;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.china.bosh.mylibrary.BuildConfig;
import com.china.bosh.mylibrary.constant.Constants;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author lzq
 * @date 2018/8/9
 */

public class RetrofitUtil {

    private ApiService mApiService;

    private RetrofitUtil(){
        changeBaseUrl("");
    }

    public void changeBaseUrl(String url){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        setSSLOptions(builder);
        Interceptor interceptor=new Interceptor(){
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long t1 = System.nanoTime();
                if(BuildConfig.DEBUG) {
                    Log.i("http请求"+"["+t1+"]",String.format("Sending request %s  %s",
                            request.url(), request.headers()));
                    String method=request.method();
                    String methodPost =  "POST";
                    if(methodPost.equals(method)){
                        if (request != null)
                        { Buffer buffer = new Buffer();
                            request.body().writeTo(buffer);
                            Log.i("http请求入参"+"["+t1+"]", "Sending RequestParams:"+ buffer.readUtf8().toString()+"}");
                        }
                    }

                }
                Response response = chain.proceed(request);
                MediaType mediaType = response.body().contentType();
                String content= response.body().string();
                long t2 = System.nanoTime();
                if(BuildConfig.DEBUG) {
                    Log.i("http请求回应" + "[" + t1 + "]", String.format("(%.1fms)Received response for %s",
                            (t2 - t1) / 1e6d, response.request().url()));
                    Log.i("http请求回应结果" + "[" + t1 + "]", content);
                }
                return response.newBuilder()
                        .body(ResponseBody.create(mediaType, content))
                        .build();
            }
        };
        builder.addInterceptor(interceptor);
        builder.connectTimeout(Constants.TIME_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIME_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TextUtils.isEmpty(url) ? Url.BASE_URL : url)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public void setSSLOptions(OkHttpClient.Builder builder) {
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //直接跳过ssl验证
                return true;
            }
        });

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            //证书中的公钥
            public static final String PUB_KEY = "3082010a0282010100d52ff5dd432b3a05113ec1a7065fa5a80308810e4e181cf14f7598c8d553cccb7d5111fdcdb55f6ee84fc92cd594adc1245a9c4cd41cbe407a919c5b4d4a37a012f8834df8cfe947c490464602fc05c18960374198336ba1c2e56d2e984bdfb8683610520e417a1a9a5053a10457355cf45878612f04bb134e3d670cf96c6e598fd0c693308fe3d084a0a91692bbd9722f05852f507d910b782db4ab13a92a7df814ee4304dccdad1b766bb671b6f8de578b7f27e76a2000d8d9e6b429d4fef8ffaa4e8037e167a2ce48752f1435f08923ed7e2dafef52ff30fef9ab66fdb556a82b257443ba30a93fda7a0af20418aa0b45403a2f829ea6e4b8ddbb9987f1bf0203010001";

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                if (x509Certificates == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkClientTrusted(x509Certificates, s);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                if (x509Certificates == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }

                if (!(x509Certificates.length > 0)) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                }

                if (!(null != s && s.equalsIgnoreCase("RSA"))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                }

                // Perform customary SSL/TLS checks
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkServerTrusted(x509Certificates, s);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
                // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                RSAPublicKey pubkey = (RSAPublicKey) x509Certificates[0].getPublicKey();

                String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                // Pin it!
                final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                if (!expected) {
                    throw new CertificateException("checkServerTrusted: Expected public key: "
                            + PUB_KEY + ", got public key:" + encoded);
                }
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            builder.sslSocketFactory(sc.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
