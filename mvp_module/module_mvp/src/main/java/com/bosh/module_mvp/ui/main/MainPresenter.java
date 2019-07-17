package com.bosh.module_mvp.ui.main;

import com.bosh.module_mvp.entity.LoginUser;
import com.bosh.module_mvp.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author lzq
 * @date 2019-07-17
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private MainModel mModel;

    @Inject
    public MainPresenter() {
        mModel = new MainModel();
    }

    @Override
    public void isLogin() {
        LoginUser user = mModel.getUser();
        if(user == null) {
            mView.goLogin();
        }
    }
}
