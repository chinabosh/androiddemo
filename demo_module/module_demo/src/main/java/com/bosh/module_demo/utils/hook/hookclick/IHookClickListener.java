package com.bosh.module_demo.utils.hook.hookclick;

import android.view.View;

/**
 * @author lzq
 * @date 2019-11-13
 */
public interface IHookClickListener {
    void onBefore(View view);
    void onAfter(View view);
}
