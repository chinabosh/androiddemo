package com.china.bosh.mylibrary.designpattern.state;

/**
 * @author lzq
 * @date 2019/5/31
 */
public class StateTwo implements State{

    @Override
    public void doSomething(StateContext context) {
        System.out.println("do something when stateTwo");
    }
}
