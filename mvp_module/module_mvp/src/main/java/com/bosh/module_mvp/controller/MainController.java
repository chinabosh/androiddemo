package com.bosh.module_mvp.controller;

import android.content.SharedPreferences;

/**
 * @author lzq
 * @date 2019-07-13
 */
public class MainController {

    private static class MainControllerInstance{
        private final static MainController INSTANCE = new MainController();
    }

    private MainController(){}

    public MainController getInstance(){
        return MainControllerInstance.INSTANCE;
    }
}
