package com.example.moviegetter.network

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DefaultSchedulersProvider : SchedulersProvider {
    override val io: Scheduler
        get() = Schedulers.io()

    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()

    override val computation: Scheduler
        get() {
            val computation1 = Schedulers.computation()
            return computation1
        }
}