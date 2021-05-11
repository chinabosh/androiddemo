package com.china.bosh.mylibrary.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

/**
 * 程序中任何一个activity处于后台时(onStope)都将触发提醒
 */
public class NewsLifecycleHandler implements Application.ActivityLifecycleCallbacks {
    // I use four separate variables here. You can, of course, just use two and
    // increment/decrement them instead of using four and incrementing them all.
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;
    private static int destroyed;
    private String msg;
    private List<String> destroyActivities = new ArrayList<>();
    private List<String> unnoticeActivities = new ArrayList<>();
    private boolean flag = true;


    /**
     * 用于执行定时任务
     */
    private Timer timer = null;

    /**
     * @param msg 程序进入后台提示用于
     */
    public NewsLifecycleHandler(String msg) {
        resetVariables();
        this.msg = msg;
    }

    /**
     * 由于销毁activity时会触发onStop方法，所以在这里设置，避免误报
     *
     * @return
     */
    public NewsLifecycleHandler setDestroyedActivity(String... activityName) {
        Collections.addAll(this.destroyActivities, activityName);
        return this;
    }

    /**
     * 该Activities处于后台时不提示
     *
     * @param activityName
     * @return
     */
    public NewsLifecycleHandler setUnnoticeActivity(String... activityName) {
        Collections.addAll(this.unnoticeActivities, activityName);
        return this;
    }

    public void resetVariables() {
        resumed = 0;
        paused = 0;
        started = 0;
        stopped = 0;
        flag = true;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i("test", "onActivityCreated-----" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        destroyed++;
        if (destroyActivities.contains(activity.getClass().getSimpleName())) {
            Log.i("test", "进入标志位判定" + activity.getClass().getSimpleName());
            flag = false;
        }
        Log.i("test", "onActivityDestroyed-----" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }


    @Override
    public void onActivityStopped(final Activity activity) {
        ++stopped;
        if (isApplicationInBackground()) {
            flag = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);//暂停500毫秒 让以下代码后于onDestroy方法执行，用来判断是否是栈内唯一Activity被销毁才触发onStop方法。
                        Log.i("test", "stopped-----" + activity.getClass().getSimpleName());
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("test", "执行前flag " + flag + " " + activity.getClass().getSimpleName());
                                if (flag && !unnoticeActivities.contains(activity.getClass().getSimpleName())) {
                                    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:


    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }

    public static boolean isApplicationInBackground() {
        return started == stopped;
    }

}
