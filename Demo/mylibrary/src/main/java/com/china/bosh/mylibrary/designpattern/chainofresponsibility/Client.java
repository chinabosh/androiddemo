package com.china.bosh.mylibrary.designpattern.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzq
 * @date 2019/5/29
 */
public class Client {
    private List<Interceptor> interceptors;

    public Client(){
        interceptors = new ArrayList<>();
    }

    public void addInterceptor(Interceptor interceptor){
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptor(){
        return interceptors;
    }
}
