package com.bosh.module_mvp.ui.login;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.BuildConfig;
import com.bosh.module_mvp.injector.BasePresenter;


import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author bosh
 * @date 2019-07-10
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter{

    private final String TAG = getClass().getName();
    private LoginContract.View mLoginView;
    private LoginContract.Model mLoginModel;

    public LoginPresenter(LoginContract.View view, LifecycleOwner owner) {
        super(owner);
        mLoginView = view;
        mLoginModel = new LoginModel();
    }

    @Override
    public void onCreate() {
        if(BuildConfig.DEBUG) {
            Log.i("LoginPresenter", "onCreate");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        super.onLifecycleChanged(owner, event);
        if(BuildConfig.DEBUG) {
            Log.i("LoginPresenter", "onLifecycleChanged, event:" + event.name());
        }
    }

    @Override
    public void login() {
        Log.i(TAG, "login");
        String account = mLoginView.getAccount();
        String pwd = mLoginView.getPassword();
        mLoginModel.login(account, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())//与view生命周期关联
                .subscribe(o -> {

                }, throwable -> {
                    throwable.printStackTrace();
                    mLoginView.showLoginFail(throwable.getMessage());
                });
    }
}
