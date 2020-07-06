package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import androidx.annotation.NonNull;


import com.china.bosh.mylibrary.constant.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author lzq
 * @date 2018/5/17
 */

public class FileUtils {


    private static boolean isSdcardExist(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getSdCardDir(){
//        StringUtil
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
     * @return log file dir
     */
    @NonNull
    public static String getLogFileDir(Context context){
        return context.getExternalFilesDir(null) + Constants.LOG_PATH;
    }

    /**
     *
     * @param src folder path which need to zipped.
     * @param zipFileString zip file path
     * @throws Exception e
     */
    public static void zipFolder(String src, String zipFileString) throws Exception{
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(zipFileString));
        File file = new File(src);
        zipFiles(file.getParent() + File.separator, file.getName(), outZip);
        outZip.finish();
        outZip.close();
    }

    private static void zipFiles(String folderString, String fileString, ZipOutputStream zos) throws Exception{
        if (zos == null) {
            return;
        }
        File file = new File(folderString + fileString);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileString);
            FileInputStream inputStream = new FileInputStream(file);
            zos.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        } else {
            String[] fileList = file.list();
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
                zos.putNextEntry(zipEntry);
                zos.closeEntry();
            }
            for (String fileListItem : fileList) {
                zipFiles(folderString+ fileString +File.separator, fileListItem, zos);
            }
        }
    }

    public static void zipLogs(String src, String zipFileString, int day) throws Exception {
        File logFile = new File(zipFileString);
        if (logFile.exists()) {
            logFile.delete();
        }
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(zipFileString));
        String dateFrom = DateUtils.getDateDaysAgo(day);
        Log.e("Test", dateFrom);
        File file = new File(src);
        if (file.isDirectory()) {
            String[] fileList = file.list();
            for (String fileListItem : fileList) {
                String date = fileListItem.substring(0, 10);
                if (date.compareTo(dateFrom) >= 0) {
                    Log.e("test", date);
                    zipFiles(src + File.separator, fileListItem, outZip);
                }
            }
        }
        outZip.finish();
        outZip.close();
    }
}
