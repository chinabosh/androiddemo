package com.china.bosh.mylibrary.designpattern.state;

/**
 * @author lzq
 * @date 2019/5/31
 */
public class StateDemo {
    public static void main(String[] args) {
        //可以通过单例模式持有context对象，统一管理状态
        StateContext context = new StateContext();
        context.doSomething();
        context.doSomething();
    }
}
