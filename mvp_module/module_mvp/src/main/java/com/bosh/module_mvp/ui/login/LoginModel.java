package com.bosh.module_mvp.ui.login;

import com.bosh.module_mvp.entity.LoginUser;
import com.bosh.module_mvp.network.NetPreFunction;
import com.bosh.module_mvp.network.ResponseData;
import com.bosh.module_mvp.network.RetrofitUtil;
import com.bosh.module_mvp.ui.base.BaseModel;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author bosh
 * @date 2019-07-10
 */
public class LoginModel extends BaseModel implements LoginContract.Model{

    @Inject
    public LoginModel() {
    }

    @Override
    public Observable<LoginUser> login(String account, String pwd) {
        return RetrofitUtil.getInstance()
                .login(account, pwd)
                .subscribeOn(Schedulers.io())
                .map(new NetPreFunction<>())
                .map(o -> {
                    Gson gson = new Gson();
                    String json = gson.toJson(o);
                    return gson.fromJson(json, LoginUser.class);
                });
    }

    @Override
    public void setUser(LoginUser user) {
        mUser = user;
    }
}
