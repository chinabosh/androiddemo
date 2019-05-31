package com.china.bosh.mylibrary.designpattern.state;

/**
 * @author lzq
 * @date 2019/5/31
 */
public class StateContext {

    public StateOne stateOne;
    public StateTwo stateTwo;
    private State state;

    public StateContext() {
        stateOne = new StateOne();
        stateTwo = new StateTwo();
        state = stateOne;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doSomething(){
        state.doSomething(this);
    }
}
