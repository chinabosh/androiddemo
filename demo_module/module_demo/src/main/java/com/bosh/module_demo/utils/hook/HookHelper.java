package com.bosh.module_demo.utils.hook;

import android.view.View;

import com.bosh.module_demo.utils.hook.hookclick.HookClickListenerProxy;
import com.bosh.module_demo.utils.hook.hookclick.IHookClickListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lzq
 * @date 2019-11-13
 */
public class HookHelper {

    public static void hookOnClickListener(View view, IHookClickListener listener) throws Exception {
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);

        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

        View.OnClickListener hookOnClickListener = new HookClickListenerProxy(originOnClickListener, listener);
        mOnClickListener.set(listenerInfo, hookOnClickListener);
    }
}