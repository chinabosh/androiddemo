package com.bosh.module_mvp.ui.login;

import io.reactivex.Observable;

/**
 * @author lzq
 * @date 2019-07-10
 */
public interface LoginContract {
    interface View {
        String getAccount();
        String getPassword();
    }

    interface Presenter{
        void login();
    }

    interface Model{
        Observable login(String account, String pwd);
    }
}
