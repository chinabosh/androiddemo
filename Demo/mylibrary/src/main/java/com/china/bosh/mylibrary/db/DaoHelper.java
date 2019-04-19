package com.china.bosh.mylibrary.db;


import com.china.bosh.mylibrary.db.table.PriceEntity;

import java.util.List;

import rx.Observable;


/**
 * @author lzq
 * @date 2018/11/22
 */

public class DaoHelper {

    private DaoSession daoSession;

    private static class DaoHelperInstance{
        private static final DaoHelper instance = new DaoHelper();
    }

    public static DaoHelper getInstance(){
        return DaoHelperInstance.instance;
    }

    private DaoHelper(){
        daoSession = DaoManager.getInstance().getDaoSession();
    }

    public void insertPriceEntity(PriceEntity entity){
        PriceEntity tmp = getPriceEntity(entity.getItemCode());
        if(tmp != null){
            entity.setId(tmp.getId());
        }
        daoSession.getPriceEntityDao().save(entity);
    }

    public Observable<Iterable<PriceEntity>> insertPriceEntityList(List<PriceEntity> list){
        return daoSession.getPriceEntityDao().rx().insertInTx(list);
    }

    public Observable<List<PriceEntity>> getPriceEntityList(){
        return daoSession.getPriceEntityDao().rx().loadAll();
    }

    /**
     *
     * @param itemName 需在两边加%，否则不能模糊查询
     * @return
     */
    public Observable<List<PriceEntity>> getPriceEntityListLike(String itemName){
        return daoSession.getPriceEntityDao().queryBuilder().where(PriceEntityDao.Properties.ItemName.like("%" + itemName + "%")).rx().list();
    }

    public PriceEntity getPriceEntity(String itemCode){
        return daoSession.getPriceEntityDao().queryBuilder().where(PriceEntityDao.Properties.ItemCode.eq(itemCode)).unique();
    }
}
