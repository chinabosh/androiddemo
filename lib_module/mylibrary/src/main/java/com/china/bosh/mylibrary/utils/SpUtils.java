package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.china.bosh.mylibrary.application.BaseApplication;

/**
 * @author lzq
 * @date 2019-07-14
 */
public class SpUtils {
    private static SpUtils sInstance;
    private static SharedPreferences sPreferences;

    public static SpUtils getInstance() {
        if (sInstance == null) {
            sInstance = new SpUtils(BaseApplication.getInstance());
        }
        return sInstance;
    }

    private SpUtils(Context context) {
        sPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sPreferences.getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return sPreferences.getFloat(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sPreferences.getLong(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return sPreferences.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putFloat(String key, float value) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean remove(String key) {
        SharedPreferences.Editor edit = sPreferences.edit();
        return edit.remove(key).commit();
    }
}
