package com.china.bosh.mylibrary.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.china.bosh.mylibrary.BuildConfig;
import com.china.bosh.mylibrary.constant.Constants;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
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
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
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
