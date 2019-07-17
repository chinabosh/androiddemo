package com.bosh.module_mvp.ui.login;

import com.bosh.module_mvp.entity.LoginUser;
import com.bosh.module_mvp.interfaces.IModel;
import com.bosh.module_mvp.interfaces.IView;
import com.bosh.module_mvp.network.ResponseData;

import io.reactivex.Observable;

/**
 * @author bosh
 * @date 2019-07-10
 */
public interface LoginContract {
    interface View extends IView {
        String getAccount();
        String getPassword();
        void showLoginFail(String msg);
        void setAccount(String account);
        void goMain();
    }

    interface Presenter{
        void login();
        void initAccount();
    }

    interface Model extends IModel {
        Observable<LoginUser> login(String account, String pwd);
        void setUser(LoginUser user);
    }
}
