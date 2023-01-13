package com.example.moviegetter.network

import io.reactivex.Scheduler

interface SchedulersProvider {
    val io: Scheduler
    val ui: Scheduler
    val computation: Scheduler
}
