package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lzq
 * @date 2018/8/1
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCaughtExceptionHandler;
    public static CrashHandler getInstance(){
        return crashHandler;
    }

    private CrashHandler(){}

    public void init(Context context){
        mDefaultCaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        if(!handleException(e) && mDefaultCaughtExceptionHandler != null){
            mDefaultCaughtExceptionHandler.uncaughtException(t,e);
        }else{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e1){
                e1.printStackTrace();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 使用 Toast 来显示异常信息
        ExecutorService single = Executors.newSingleThreadExecutor();
        single.execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,"很抱歉，程序出现异常，即将退出",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });
        single.shutdown();

        try {
            //导出异常信息到SD卡中
            LogUtils.e2File("crashHandler",ex.getMessage());
//            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
//            _uploadExceptionToServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
