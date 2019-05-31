package com.china.bosh.mylibrary.designpattern.state;

/**
 * @author lzq
 * @date 2019/5/31
 */
public class StateOne implements State {

    @Override
    public void doSomething(StateContext context) {
        System.out.println("doSomething when stateOne");
        context.setState(context.stateTwo);
    }
}
