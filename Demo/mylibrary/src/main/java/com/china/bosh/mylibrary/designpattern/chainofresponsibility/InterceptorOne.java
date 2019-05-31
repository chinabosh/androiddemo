package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author lzq
 * @date 2019/5/29
 */
public class InterceptorOne implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //do something before request
        System.out.println("InterceptorOne do something before request");
        Response response = chain.proceed(request);
        //do something after request
        System.out.println("InterceptorOne do something after request");
        return response;
    }
}
