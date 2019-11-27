package com.china.bosh.mylibrary.retrofit;

import com.china.bosh.mylibrary.utils.Base64Util;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author lzq
 * @date 2019-09-05
 */
public class ResponseDecryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if(response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if(responseBody != null) {
                try {
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer = source.buffer();
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = responseBody.contentType();
                    if(contentType != null) {
                        charset = contentType.charset(charset);
                    }
                    String bodyString = buffer.clone().readString(charset);
                    String responseData = Base64Util.decode(bodyString);
                    ResponseBody newResponseBody = ResponseBody.create(contentType, responseData.trim());
                    response = response.newBuilder().body(newResponseBody).build();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
