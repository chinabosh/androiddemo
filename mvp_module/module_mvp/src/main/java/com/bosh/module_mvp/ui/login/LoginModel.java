package com.bosh.module_mvp.ui.login;

import com.china.bosh.mylibrary.retrofit.RetrofitUtil;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author lzq
 * @date 2019-07-10
 */
public class LoginModel implements LoginContract.Model{
    @Inject LoginPresenter loginPresenter;

    @Inject
    public LoginModel() {

    }

    @Override
    public Observable login(String account, String pwd) {
        return null;
    }
}
