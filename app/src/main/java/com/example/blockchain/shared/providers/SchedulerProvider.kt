package com.example.blockchain.shared.providers

import io.reactivex.rxjava3.core.Scheduler

/**
 * @author Leonardo Martins on 15/11/20
 */
interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler
}
