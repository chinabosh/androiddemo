package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.io.IOException;

import okhttp3.Request;

/**
 * @author lzq
 * @date 2019/5/29
 */
public class ChainDemo {
    public static void main(String[] args) {
        Client client = new Client();
        InterceptorOne interceprotOne = new InterceptorOne();
        client.addInterceptor(interceprotOne);
        client.addInterceptor(new InterceptorLast());
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        RealInterceptorChain chain = new RealInterceptorChain(client.getInterceptor(), 0, request);
        try {
            chain.proceed(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
