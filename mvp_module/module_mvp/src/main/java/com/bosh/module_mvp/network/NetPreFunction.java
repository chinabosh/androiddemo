package com.bosh.module_mvp.network;

import com.bosh.module_mvp.R;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author lzq
 * @date 2019-07-12
 */
public class NetPreFunction<T extends ResponseData<R>, R extends Object> implements Function<T, R> {
    @Override
    public R apply(T t) throws Exception {
        if("800".equals(t.getCode())) {
         return t.getData();
        } else {
            return null;
        }
    }
}
