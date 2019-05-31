package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author lzq
 * @date 2019/5/29
 */
public interface Interceptor {
    okhttp3.Response intercept(Interceptor.Chain chain) throws IOException;

    interface Chain {
        okhttp3.Request request();

        Response proceed(Request request) throws IOException;

        Connection connection();
    }
}
