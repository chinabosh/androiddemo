package com.bosh.module_demo.utils.hook.hookclick;

import android.view.View;

/**
 * @author lzq
 * @date 2019-11-13
 */
public class HookClickListenerProxy implements View.OnClickListener {

    private View.OnClickListener origin;
    private IHookClickListener listener;

    public HookClickListenerProxy(View.OnClickListener origin, IHookClickListener listener) {
        this.origin = origin;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onBefore(v);
        }
        if(origin != null) {
            origin.onClick(v);
        }
        if(listener != null) {
            listener.onAfter(v);
        }
    }
}
