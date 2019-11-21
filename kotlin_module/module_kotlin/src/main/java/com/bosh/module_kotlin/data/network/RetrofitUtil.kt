package com.bosh.module_kotlin.data.network

/**
 * @author lzq
 * @date  2019-11-21
 */
class RetrofitUtil {
    private val commonService = ServiceCreator.create(CommonService::class.java)

    companion object {
        private var retrofitUtil : RetrofitUtil? = null

        fun getInstance() : RetrofitUtil {
            if(retrofitUtil == null) {
                synchronized(RetrofitUtil::class.java) {
                    if(retrofitUtil == null) {
                        retrofitUtil = RetrofitUtil()
                    }
                }
            }
            return retrofitUtil!!
        }
    }
}