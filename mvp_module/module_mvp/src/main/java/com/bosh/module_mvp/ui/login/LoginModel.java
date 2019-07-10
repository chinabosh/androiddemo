package com.bosh.module_mvp.ui.login;

import javax.inject.Inject;

/**
 * @author lzq
 * @date 2019-07-10
 */
public class LoginModel implements LoginContract.Model{
    @Inject LoginPresenter loginPresenter;

    @Inject
    public LoginModel() {

    }
}
