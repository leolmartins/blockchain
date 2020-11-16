package com.example.blockchain

import com.example.blockchain.shared.providers.SchedulerProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.TestScheduler

/**
 * @author Leonardo Martins on 16/11/20
 */
class TestSchedulerProvider(
    private val testScheduler: TestScheduler
) : SchedulerProvider {

    override fun ui(): Scheduler = testScheduler

    override fun io(): Scheduler = testScheduler

    override fun computation(): Scheduler = testScheduler
}
