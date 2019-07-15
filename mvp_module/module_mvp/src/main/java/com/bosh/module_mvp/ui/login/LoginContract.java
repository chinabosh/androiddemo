package com.bosh.module_mvp.ui.login;

import com.bosh.module_mvp.network.ResponseData;

import io.reactivex.Observable;

/**
 * @author lzq
 * @date 2019-07-10
 */
public interface LoginContract {
    interface View {
        String getAccount();
        String getPassword();
        void showLoginFail(String msg);
    }

    interface Presenter{
        void login();
    }

    interface Model{
        <R> Observable<R> login(String account, String pwd);
    }
}
