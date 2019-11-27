package com.china.bosh.mylibrary.db;

import android.util.Log;

import com.china.bosh.mylibrary.db.table.PriceEntity;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author lzq
 * @date 2018/11/22
 */

public class DaoModel {

    private static class DaoModelInstance{
        private static final DaoModel instance = new DaoModel();
    }

    private DaoModel(){}

    public static DaoModel getInstance(){
        return DaoModelInstance.instance;
    }

//    public void saveAllPriceItem(final List<PayItem> list){
//        Observable.fromIterable(list)
//                .subscribeOn(Schedulers.io())
//                .map(new Function<PayItem, PriceEntity>() {
//                    @Override
//                    public PriceEntity apply(PayItem payItem) throws Exception {
//                        String json = JacksonUtil.toJson(payItem);
//                        return new Gson().fromJson(json, PriceEntity.class);
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<PriceEntity>() {
//                    @Override
//                    public void accept(PriceEntity entity) throws Exception {
//                        DaoHelper.getInstance().insertPriceEntity(entity);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        EventBusUtil.sendEvent(EventCode.SAVE_PRICE_LIST_TO_DB);
//                    }
//                });
//
//    }
//
//    public void getAllPriceItem(){
//        DaoHelper.getInstance().getPriceEntityList()
//                .subscribeOn(rx.schedulers.Schedulers.io())
//                .flatMapIterable(new Func1<List<PriceEntity>, Iterable<PriceEntity>>() {
//                    @Override
//                    public Iterable<PriceEntity> call(List<PriceEntity> priceEntities) {
//                        return priceEntities;
//                    }
//                })
//                .map(new Func1<PriceEntity, PayItem>() {
//                    @Override
//                    public PayItem call(PriceEntity entity) {
//                        String json = JacksonUtil.toJson(entity);
//                        Log.e("test", "json:" + json);
//                        return new Gson().fromJson(json, PayItem.class);
//                    }
//                })
//                .toList()
//                .subscribe(new Action1<List<PayItem>>() {
//                    @Override
//                    public void call(List<PayItem> payItems) {
//                        EventBusUtil.sendEvent(EventCode.GET_PRICE_LIST_FROM_DB, payItems);
//                    }
//                });
//    }
//
//    public void getPriceItemsLike(String itemName){
//        DaoHelper.getInstance().getPriceEntityListLike(itemName)
//                .subscribeOn(rx.schedulers.Schedulers.io())
//                .flatMapIterable(new Func1<List<PriceEntity>, Iterable<PriceEntity>>() {
//                    @Override
//                    public Iterable<PriceEntity> call(List<PriceEntity> priceEntities) {
//                        return priceEntities;
//                    }
//                })
//                .map(new Func1<PriceEntity, PayItem>() {
//                    @Override
//                    public PayItem call(PriceEntity entity) {
//                        String json = JacksonUtil.toJson(entity);
//                        Log.e("test", "json:" + json);
//                        return new Gson().fromJson(json, PayItem.class);
//                    }
//                })
//                .toList()
//                .subscribe(new Action1<List<PayItem>>() {
//                    @Override
//                    public void call(List<PayItem> payItems) {
//                        EventBusUtil.sendEvent(EventCode.GET_PRICE_LIST_FROM_DB, payItems);
//                    }
//                });
//    }
}
