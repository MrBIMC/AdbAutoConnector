package com.pavelsikun.kotlin.adbautoconnector

import java.util.*

/**
 * Created by mrbimc on 04.07.15.
 */

/**
 *  Sorta like AsyncTask from android, but it stops from returning data after given
 *  @param countdown is elapsed
 */
abstract class TimeoutTask<T>(val countdown: Long) {
    private val thread = Thread({
        val result = doInBackground()
        if (!Thread.currentThread().isInterrupted()) {
            onComplete(result)
            Thread.currentThread().interrupt()
        }
    })

    private val countDownTask = object: TimerTask() {
        override fun run() {
            if(!thread.isInterrupted()) {
                thread.interrupt()
                onInterrupt()
            }
        }
    }

    fun start() {
        thread.start()
        Timer().schedule(countDownTask, countdown)
    }

    /**
     *  @param result returns data if task executed successfully
     */
    abstract fun onComplete(result: T)

    /**
     *  Function that is being executed in background
     */
    abstract fun doInBackground(): T

    /**
     *  Function that is being called when timer ran out
     */
    abstract fun onInterrupt()
}
