package com.bosh.module_mvp.ui.login;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.BuildConfig;
import com.bosh.module_mvp.constant.Constants;
import com.bosh.module_mvp.ui.base.BasePresenter;
import com.bosh.module_mvp.ui.base.BaseActivity;
import com.china.bosh.mylibrary.utils.SpUtils;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author bosh
 * @date 2019-07-10
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private final String TAG = getClass().getName();
    private LoginContract.Model mModel;

    @Inject
    public LoginPresenter(BaseActivity activity) {
        mModel = new LoginModel();
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
        mView.showLoading();
        String account = mView.getAccount();
        String pwd = mView.getPassword();
        mModel.login(account, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())//与view生命周期关联
                .subscribe(user -> {
                    SpUtils.getInstance().putString(Constants.SP_ACCOUNT, account);
                    mModel.setUser(user);
                    mView.goMain();
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showLoginFail(throwable.getMessage());
                    mView.hideLoading();
                }, () -> mView.hideLoading());
    }

    @Override
    public void initAccount() {
        String account = SpUtils.getInstance().getString(Constants.SP_ACCOUNT, "");
        if(!TextUtils.isEmpty(account)) {
            mView.setAccount(account);
        }
    }
}
