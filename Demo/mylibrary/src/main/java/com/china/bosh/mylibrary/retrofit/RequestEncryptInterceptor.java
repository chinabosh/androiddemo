package com.china.bosh.mylibrary.retrofit;

import com.china.bosh.mylibrary.BuildConfig;
import com.china.bosh.mylibrary.constant.Constants;
import com.china.bosh.mylibrary.utils.Base64Util;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;

/**
 * @author lzq
 * @date 2019-09-05
 */
public class RequestEncryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Charset charset = Charset.forName("UTF-8");
        String method = request.method().toLowerCase().trim();
        HttpUrl url = request.url();
        String apiPath = (url.scheme() + "://" + url.host() + ":" + url.port() + url.encodedPath()).trim();
        String serverPath = (url.scheme() + "://" + url.host() + "/");
        if(!serverPath.startsWith(Url.BASE_URL)) {
            return chain.proceed(request);
        }
        if("get".equals(method) || "delete".equals(method)) {
            if(url.encodedQuery() != null) {
                try {
                    String queryparamNames = url.encodedQuery();
                    String encryptQuery = Base64Util.encode(queryparamNames);
                    String newUrl = apiPath + "?param=" + encryptQuery;
                    request = request.newBuilder().url(newUrl).build();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            RequestBody requestBody = request.body();
            if(requestBody != null) {
                MediaType contentType = requestBody.contentType();
                if(contentType != null) {
                    charset = contentType.charset(charset);
                    if("multipart".equals(contentType.type().toLowerCase())) {
                        return chain.proceed(request);
                    }
                }

                try {
                    BufferedSink sink = new Buffer();
                    requestBody.writeTo(sink);
                    String requestData = URLDecoder.decode(((Buffer) sink).readString(charset).trim(), "utf-8");
                    String encryptData = Base64Util.encode(requestData);
                    RequestBody newRequestBody = RequestBody.create(contentType, encryptData);
                    Request.Builder builder = request.newBuilder();
                    if("post".equals(method)) {
                        builder.post(newRequestBody);
                    } else if("put".equals(method)) {
                        builder.put(newRequestBody);
                    }
                    request = builder.build();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return chain.proceed(request);
            }
        }
        return null;
    }
}
