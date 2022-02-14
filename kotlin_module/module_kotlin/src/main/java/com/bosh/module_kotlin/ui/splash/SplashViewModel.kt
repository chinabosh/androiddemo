package com.bosh.module_kotlin.ui.splash

import com.bosh.module_kotlin.base.BaseViewModel
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

/**
 * @author lzq
 * @date 2019-11-14
 */
class SplashViewModel : BaseViewModel() {

    private val countTimeSubject = BehaviorSubject.create<Int>()

    fun startCountTime() {
        val count = 6L
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(count)
                .autoDisposable(this)
                .subscribe ({
                    countTimeSubject.onNext((count - 1 - it).toInt())
                }, {
                    countTimeSubject.onError(it)
                }, {
                    countTimeSubject.onComplete()
                })
    }

    fun observeCountTime() : Observable<Int> {
        return countTimeSubject
    }
}
