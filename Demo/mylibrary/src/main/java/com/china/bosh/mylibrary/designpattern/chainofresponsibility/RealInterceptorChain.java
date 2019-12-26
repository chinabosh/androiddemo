package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.io.IOException;
import java.util.List;

import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author lzq
 * @date 2019/5/29
 */
public class RealInterceptorChain implements Interceptor.Chain {

    private Request request;
    private List<Interceptor> interceptors;
    private int index;


    public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        if(index >= interceptors.size()) {
            throw new AssertionError();
        }
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request);
        Interceptor interceptor = interceptors.get(index);
        return interceptor.intercept(next);
    }

    @Override
    public Connection connection() {
        return null;
    }
}
