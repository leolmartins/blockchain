package com.example.blockchain.shared.services

import com.example.blockchain.shared.model.BlockChainStatsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * @author Leonardo Martins on 15/11/20
 */
interface StatsService {

    @GET("stats")
    fun fetchBlockChainStats(): Single<BlockChainStatsResponse>
}
