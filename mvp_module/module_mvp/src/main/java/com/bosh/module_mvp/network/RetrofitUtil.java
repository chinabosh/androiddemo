package com.bosh.module_mvp.network;

import android.text.TextUtils;
import android.util.Log;

import com.bosh.module_mvp.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author bosh
 * @date 2019-07-12
 */
@SuppressWarnings("unchecked")
public class RetrofitUtil {
    private ApiService mApiService;
    private Retrofit retrofit;

    private static class RetrofitInstance {
        private final static RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    public static RetrofitUtil getInstance() {
        return RetrofitInstance.INSTANCE;
    }

    private RetrofitUtil() {
        changeUrl("");
    }

    public void changeUrl(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            long t1 = System.nanoTime();
            if (BuildConfig.DEBUG) {
                Log.i("http请求" + "[" + t1 + "]", String.format("Sending request %s  %s",
                        request.url(), request.headers()));
                String method = request.method();
                if ("POST".equals(method)) {
                    Buffer buffer = new Buffer();
                    if (buffer.size() <= 10000000) {
                        if (request.body() != null) {
                            request.body().writeTo(buffer);
                        }
                        Log.i("http请求入参" , "[" + t1 + "]" + "Sending RequestParams:" + buffer.readUtf8());
                    }
                }

            }
            Response response = chain.proceed(request);
            assert response.body() != null;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            long t2 = System.nanoTime();
            if (BuildConfig.DEBUG) {
                Log.i("http请求回应" + "[" + t1 + "]" , String.format("(%.1fms)Received response for %s",
                        (t2 - t1) / 1e6d, response.request().url()));
                Log.i("http请求回应结果" , "[" + t1 + "]" + content);
            }
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        };
        builder.addInterceptor(interceptor);
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(TextUtils.isEmpty(url) ? Url.BASE_URL : url)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public <T> Observable<ResponseData<T>> login(String account, String pwd) {
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("account", account);
        map.put("pwd", pwd);
        return mApiService.login(map);
    }
}
