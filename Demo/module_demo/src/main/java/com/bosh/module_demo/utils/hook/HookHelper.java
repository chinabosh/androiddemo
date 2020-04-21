package com.bosh.module_demo.utils.hook;

import android.util.Log;
import android.view.View;

import com.bosh.module_demo.ui.activity.HookClickActivity;
import com.bosh.module_demo.utils.hook.hookclick.HookClickListenerProxy;
import com.bosh.module_demo.utils.hook.hookclick.IHookClickListener;
import com.china.bosh.mylibrary.annotation.Open;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lzq
 * @date 2019-11-13
 */
@Open
public class HookHelper {

    @SuppressWarnings("PrivateApi")
    public static void hookOnClickListener(View view, IHookClickListener listener) throws Exception {
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);

        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

        if(originOnClickListener instanceof HookClickListenerProxy) {
            Log.i("test","has already hooked");
            return;
        }
        View.OnClickListener hookOnClickListener = new HookClickListenerProxy(originOnClickListener, listener);
        mOnClickListener.set(listenerInfo, hookOnClickListener);
    }
}
