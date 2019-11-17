package com.bosh.module_kotlin.base

import androidx.lifecycle.ViewModel
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author lzq
 * @date  2019-11-17
 */
open class BaseViewModel : ViewModel(), LifecycleScopeProvider<BaseViewModel.ViewModelEvent> {

    private val lifecycleEvent = BehaviorSubject.createDefault(ViewModelEvent.CREATED)

    override fun lifecycle(): Observable<ViewModelEvent> {
        return lifecycleEvent.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent> {
        return CORRESPONDING_EVENTS
    }

    override fun peekLifecycle(): ViewModelEvent? {
        return lifecycleEvent.value
    }

    enum class ViewModelEvent {
        CREATED, CLEARED
    }

    override fun onCleared() {
        lifecycleEvent.onNext(ViewModelEvent.CLEARED)
        super.onCleared()
    }

    companion object {
        /**
         * Function of current event -> target disposal event. ViewModel has a very simple lifecycle.
         * It is created and then later on cleared. So we only have two events and all subscriptions
         * will only be disposed at [ViewModelEvent.CLEARED].
         */
        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> { event ->
            when (event) {
                ViewModelEvent.CREATED -> ViewModelEvent.CLEARED
                else -> throw LifecycleEndedException(
                        "Cannot bind to ViewModel lifecycle after onCleared.")
            }
        }
    }
}