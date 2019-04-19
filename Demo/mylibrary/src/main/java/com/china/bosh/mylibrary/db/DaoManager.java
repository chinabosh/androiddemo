package com.china.bosh.mylibrary.db;

import android.content.Context;

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
    private static DaoMaster.DevOpenHelper helper;

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
        helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
