package com.bosh.module_kotlin.extension

import android.os.SystemClock
import android.view.View

/**
 * click debounce
 * 拦截点击请求，超过上次点击一定时间以后才响应
 * @author lzq
 * @date  2020/8/6
 */
fun View.setOnDebounceClickListener(action: () -> Unit) {
    val actionDebounce = ActionDebounce(action)

    setOnClickListener {
        actionDebounce.action()
    }
}

private class ActionDebounce(private val action: () -> Unit) {
    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun action() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}