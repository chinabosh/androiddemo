package com.china.bosh.mylibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

/**
 * @author lzq
 * @date 2019/4/28
 */
@SuppressWarnings("unchecked")
public class MyOpenHelper extends DaoMaster.DevOpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        Log.e("test", "database version:" + db.getVersion());
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
        Log.e("test", "数据库升级");
//        if (oldVersion == 1 && newVersion > 1) {
//            //PriceEntity增加字段itemNamePinyin
//            MigrationHelper.getInstance().migrate(db, PriceEntityDao.class);
//        }
//        if (oldVersion <= 3 && newVersion >= 4) {
//            //PriceEntity增加字段orderNo
//            MigrationHelper.getInstance().migrate(db, PriceEntityDao.class);
//        }
    }
}
