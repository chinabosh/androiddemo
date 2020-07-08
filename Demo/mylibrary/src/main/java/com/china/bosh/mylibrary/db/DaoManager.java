package com.china.bosh.mylibrary.db;

import android.content.Context;
import android.util.Log;

import com.china.bosh.mylibrary.BuildConfig;

import org.greenrobot.greendao.database.Database;

/**
 * @author lzq
 * @date 2018/11/22
 * TODO 完善工具
 */

public class DaoManager {

    private static final String DB_NAME = "test_db";
    private static DaoManager daoManager;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static MyOpenHelper helper;

    private DaoManager(){}

    public static DaoManager getInstance(){
        if(daoManager == null){
            synchronized (DaoManager.class){
                if(daoManager == null){
                    daoManager = new DaoManager();
                }
            }
        }
        return daoManager;
    }

    public void init(Context context){
        helper = new MyOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        if (BuildConfig.DEBUG) {
            Log.e("test", "数据库版本：" + helper.getWritableDatabase().getVersion());
        }
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
