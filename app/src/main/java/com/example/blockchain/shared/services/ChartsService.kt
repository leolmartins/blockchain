package com.example.blockchain.shared.services

import com.example.blockchain.shared.model.ChartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Leonardo Martins on 15/11/20
 */
interface ChartsService {

    @GET("charts/market-price")
    fun fetchChart(
        @Query("timespan") timeSpan: String,
        @Query("sampled") sampled: Boolean
    ): Single<ChartResponse>
}
