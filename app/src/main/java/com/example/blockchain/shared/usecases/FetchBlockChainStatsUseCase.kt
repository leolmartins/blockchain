package com.example.blockchain.shared.usecases

import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.services.StatsService
import io.reactivex.rxjava3.core.Single

/**
 * @author Leonardo Martins on 15/11/20
 */
interface FetchBlockChainStatsUseCase {

    fun fetchBlockChainStats(): Single<BlockChainStats>
}

class FetchBlockChainStatsUseCaseImpl(
    private val statsService: StatsService
) : FetchBlockChainStatsUseCase {

    override fun fetchBlockChainStats(): Single<BlockChainStats> =
        statsService.fetchBlockChainStats().map { response ->
            BlockChainStats(
                response.marketPriceUsd
            )
        }
}
