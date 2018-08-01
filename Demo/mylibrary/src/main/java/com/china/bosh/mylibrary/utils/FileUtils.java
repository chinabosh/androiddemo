package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.china.bosh.mylibrary.BuildConfig;
import com.china.bosh.mylibrary.constant.Constants;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author lzq
 * @date 2018/5/17
 */

public class FileUtils {


    private static boolean isSdcardExist(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getSdCardDir(){
        if(isSdcardExist()){
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    private static String[] getStoragePath(Context mContext, boolean is_removale) {

        String[] paths = new String[8];
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    paths[i] = path;
                }
            }
            return paths;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param context applicationContext is better
     * @return
     */
    @NonNull
    public static String getLogFileDir(Context context){
        return context.getExternalFilesDir(null) + Constants.LOG_PATH;
    }
}
