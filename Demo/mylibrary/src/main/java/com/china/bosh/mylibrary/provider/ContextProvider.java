package com.china.bosh.mylibrary.provider;

import android.content.Context;

/**
 * @author lzq
 * @date 2020-03-30
 */
public class ContextProvider {

    private static ContextProvider instance;

    private Context mContext;

    private ContextProvider(Context context) {
        mContext = context;
    }

    public static ContextProvider get() {
        if (instance == null) {
            synchronized (ContextProvider.class) {
                if (instance == null) {
                    Context context = ApplicationContextProvider.mContext;
                    if (context == null) {
                        throw new IllegalStateException("context == null");
                    }
                    instance = new ContextProvider(context);
                }
            }
        }
        return instance;
    }

    public Context getContext() {
        return mContext;
    }
}
