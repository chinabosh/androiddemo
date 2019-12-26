package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.io.IOException;

import okhttp3.Protocol;
import okhttp3.Response;

/**
 * @author lzq
 * @date 2019/5/29
 */
public class InterceptorLast implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println("last chain, do the real thing and don't proceed");
        //最后一个就不再从interceptors中再获取interceptor执行了，直接做实际要做的
        return new Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(400)
                .build();
    }
}
