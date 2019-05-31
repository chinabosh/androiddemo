package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.StringRes;
import android.widget.Toast;

import com.china.bosh.mylibrary.application.BaseApplication;


/**
 * @author lzq
 * @date 2018/9/7
 */

public class ToastUtil {
    private static Toast toast;
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static Context context;

    private static void checkNull(){
        if(toast == null){
            context = BaseApplication.instance.getApplicationContext();
            toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
        }
    }

    public static void showShort(@StringRes final int id){
        show(id, Toast.LENGTH_SHORT);
    }

    public static void showShort(final String msg){
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void showLong(@StringRes final int id){
        show(id, Toast.LENGTH_LONG);
    }

    public static void showLong(final String msg){
        show(msg, Toast.LENGTH_LONG);
    }

    public static void show(@StringRes final int id, final int duration){
        show(context.getString(id), duration);
    }

    public static void show(final String msg, final int duration){
        checkNull();
        handler.post(new Runnable() {
            @Override
            public void run() {
                toast.setDuration(duration);
                toast.setText(msg);
                toast.show();
            }
        });
    }

}
