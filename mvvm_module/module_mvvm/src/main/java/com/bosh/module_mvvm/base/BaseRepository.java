package com.bosh.module_mvvm.base;

import com.china.bosh.mylibrary.retrofit.ApiService;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author lzq
 * @date 2019-07-31
 */
public abstract class BaseRepository {
    protected ApiService apiService;

    public BaseRepository() {
        if(null == apiService) {

        }
    }

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if(mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if(mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}
