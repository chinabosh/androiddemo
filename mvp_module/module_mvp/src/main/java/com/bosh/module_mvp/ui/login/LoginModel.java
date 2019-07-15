package com.bosh.module_mvp.ui.login;

import com.bosh.module_mvp.network.NetPreFunction;
import com.bosh.module_mvp.network.ResponseData;
import com.bosh.module_mvp.network.RetrofitUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lzq
 * @date 2019-07-10
 */
public class LoginModel implements LoginContract.Model{

    @Inject
    public LoginModel() {

    }

    @Override
    public <R> Observable<R> login(String account, String pwd) {
        return RetrofitUtil.getInstance()
                .login(account, pwd)
                .subscribeOn(Schedulers.io())
                .map(new NetPreFunction<>());
    }
}
