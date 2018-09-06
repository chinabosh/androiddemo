package com.china.bosh.mylibrary.utils;

import android.content.Context;
import android.util.Log;


import com.china.bosh.mylibrary.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * log工具类
 * @author lzq
 * @date 2018/7/17
 */
@SuppressWarnings("unused")
public class LogUtils {

    private static String TEST = "test";

    private static boolean isDebug = false;

    /**
     * log日志存放路径
     */
    private static String logPath = "";

    /**
     * 日期格式
     * 一小时一个log文件
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH", Locale.CHINA);

    private static SimpleDateFormat logDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS",Locale.CHINA);

    private static Date date = new Date();

    private static ArrayBlockingQueue<String> logList = new ArrayBlockingQueue<>(100);

    private static long ONE_DAY = 24 * 60 * 60 * 1000;

    private static boolean isRunning = false;

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param context applicationContext
     */
    public static void init(Context context) {
        //获得文件储存路径
        logPath = getFilePath(context);
        isDebug = BuildConfig.isDebug;
        isRunning = true;
        startWriteLog();
    }

    /**
     * 获得文件存储路径
     *
     * @return 文件存储路径
     */
    private static String getFilePath(Context context) {
        return FileUtils.getLogFileDir(context.getApplicationContext());
    }

    private static final char VERBOSE = 'v';

    private static final char DEBUG = 'd';

    private static final char INFO = 'i';

    private static final char WARN = 'w';

    private static final char ERROR = 'e';

    public static void v2File(String tag, String msg) {
//        writeToFile(VERBOSE, tag, msg);
        String log = "v/" + tag + ": " + msg;
        logList.offer(log);
    }

    public static void v(String msg){
        if(BuildConfig.DEBUG) {
            Log.v(TEST, msg);
        }
    }

    public static void d2File(String tag, String msg) {
//        writeToFile(DEBUG, tag, msg);
        String log = "d/" + tag + ": " + msg;
        logList.offer(log);
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    public static void d(String msg){
        if(BuildConfig.DEBUG) {
            Log.d(TEST,msg);
        }
    }

    public static void i2File(String tag, String msg) {
//        writeToFile(INFO, tag, msg);
        String log = "i/" + tag + ": " + msg;
        logList.offer(log);
        if(isDebug){
            Log.i(tag,msg);
        }
    }

    public static void i(String msg){
        if(BuildConfig.DEBUG){
            Log.d(TEST,msg);
        }
    }

    public static void w2File(String tag, String msg) {
//        writeToFile(WARN, tag, msg);
        String log = "w/" + tag + ": " + msg;
        logList.offer(log);
        if(isDebug){
            Log.w(tag,msg);
        }
    }

    public static void w(String msg){
        if(BuildConfig.DEBUG){
            Log.w(TEST,msg);
        }
    }

    public static void e2File(String tag, String msg) {
//        writeToFile(ERROR, tag, msg);
        String log = "e/" + tag + ": " + msg;
        logList.offer(log);
        if(isDebug){
            Log.e(tag,msg);
        }
    }

    public static void e(String msg){
        if(BuildConfig.DEBUG){
            Log.e(TEST,msg);
        }
    }

    @SuppressWarnings("")
    public static void deleteLogFileInDays(final int day){
        String filename = logPath + "/";
        File file = new File(filename);
        if(!file.exists()){
            return;
        }
        final File[] logFiles = file.listFiles();
        for(File logFile:logFiles){
            long lastModified = logFile.lastModified();
            Calendar calendar = Calendar.getInstance();
            if(calendar.getTimeInMillis() - lastModified > day * ONE_DAY){
                if(!logFile.delete()){
                    i2File("LogUtils",logFile.getName() + " delete fail");
                }
            }
        }
    }

    public static void stopWriteLog(){
        isRunning = false;
    }

    /**
     * 使用线程写log
     */
    private static void startWriteLog(){
        if(null == logPath){
            return;
        }
        ExecutorService single = Executors.newSingleThreadExecutor();
        single.execute(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    String msg = logList.poll();
                    if(msg == null){
                        continue;
                    }
                    //log日志名，使用时间命名，保证不重复
                    String fileName = logPath + "/" + dateFormat.format(new Date()) + ".log";
                    //log日志内容，可以自行定制
                    String log = "\r\n" + logDateFormat.format(new Date()) + ":" + msg;

                    //如果父路径不存在
                    File file = new File(logPath);
                    if (!file.exists()) {
                        file.mkdirs();//创建父路径
                    }

                    //FileOutputStream会自动调用底层的close()方法，不用关闭
                    FileOutputStream fos;
                    BufferedWriter bw = null;
                    try {
                        //这里的第二个参数代表追加还是覆盖，true为追加，false为覆盖
                        fos = new FileOutputStream(fileName, true);
                        bw = new BufferedWriter(new OutputStreamWriter(fos));
                        bw.write(log);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();//关闭缓冲流
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        single.shutdown();
    }

    /**
     * 将log信息写入文件中
     *
     * @param type unused
     * @param tag d,i,w,e
     * @param msg log
     */
    private static void writeToFile(char type, String tag, String msg) {
        if (null == logPath) {
            String TAG = "LogToFile";
            Log.e(TAG, "logPath == null ，未初始化LogToFile");
            return;
        }

        //log日志名，使用时间命名，保证不重复
        String fileName = logPath + "/" + dateFormat.format(new Date()) + ".log";
        //log日志内容，可以自行定制
        String log = "\n" + logDateFormat.format(new Date()) + ":" + msg;

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        //FileOutputStream会自动调用底层的close()方法，不用关闭
        FileOutputStream fos;
        BufferedWriter bw = null;
        try {

            //这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
