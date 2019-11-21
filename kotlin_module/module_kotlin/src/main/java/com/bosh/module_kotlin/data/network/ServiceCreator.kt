package com.bosh.module_kotlin.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author lzq
 * @date  2019-11-21
 */
object ServiceCreator {
    private const val BASE_URL = "http://127.0.0.1"

    private val okHttpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


    fun <T> create(serviceClass: Class<T>, url:String = BASE_URL): T = builder.baseUrl(url).build().create(serviceClass)
}