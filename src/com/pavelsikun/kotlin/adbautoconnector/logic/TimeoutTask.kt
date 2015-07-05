package com.pavelsikun.kotlin.adbautoconnector.logic

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
        onComplete(result)
    })

    private val countDownTask = object: TimerTask() {
        override fun run(){
            thread.interrupt()
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
}
