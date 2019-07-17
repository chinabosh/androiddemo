package com.bosh.module_mvp.interfaces;

import androidx.annotation.StringRes;

/**
 * @author bosh
 * @date 2019-07-16
 */
public interface IView {
    void showLoading();
    void showLoading(String content);
    void hideLoading();
    void toast(@StringRes int id);
    void toast(String msg);
    void toastLong(@StringRes int id);
    void toastLong(String msg);
}
