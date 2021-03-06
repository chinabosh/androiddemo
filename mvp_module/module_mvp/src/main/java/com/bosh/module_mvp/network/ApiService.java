package com.bosh.module_mvp.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author bosh
 * @date 2019-07-12
 */
public interface ApiService {

    @POST(Url.LOGIN)
    <T> Observable<ResponseData<T>> login(@Body Map map);
}
