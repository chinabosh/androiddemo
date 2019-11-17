package com.bosh.module_kotlin.ui.splash

import android.os.CountDownTimer
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.bosh.module_kotlin.base.BaseViewModel
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

/**
 * @author lzq
 * @date 2019-11-14
 */
class SplashViewModel : BaseViewModel() {

    private val countTimeSubject = BehaviorSubject.create<Int>()

    fun startCountTime() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .autoDisposable(this)
                .subscribe {
                    countTimeSubject.onNext(it.toInt())
                }
    }

    fun observeCountTime() : Observable<Int> {
        return countTimeSubject.hide().distinctUntilChanged()
    }
}
