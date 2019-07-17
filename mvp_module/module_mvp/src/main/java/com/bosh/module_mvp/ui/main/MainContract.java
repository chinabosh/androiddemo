package com.bosh.module_mvp.ui.main;

import com.bosh.module_mvp.interfaces.IModel;
import com.bosh.module_mvp.interfaces.IView;

/**
 * @author lzq
 * @date 2019-07-17
 */
public interface MainContract {
    interface View extends IView{
        void goLogin();
    }

    interface Presenter {
        void isLogin();
    }

    interface Model extends IModel{

    }
}
