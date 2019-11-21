package com.bosh.module_kotlin.data.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author lzq
 * @date  2019-11-21
 */
interface CommonService {
    @POST("common.action=login")
    fun login(@Body map: Map<String, Any>) : Observable<Any>
}