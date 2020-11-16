package com.example.blockchain.shared.usecases

import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.services.ChartsService
import io.reactivex.rxjava3.core.Single

/**
 * @author Leonardo Martins on 15/11/20
 */
interface FetchBlockChainChartUseCase {

    fun fetchBlockChainChart(timeSpan: ChartTimeSpan): Single<Chart>
}

class FetchBlockChainChartUseCaseImpl(
    private val chartsService: ChartsService
) : FetchBlockChainChartUseCase {

    override fun fetchBlockChainChart(timeSpan: ChartTimeSpan): Single<Chart> =
        chartsService.fetchChart(timeSpan.value, true).map { response ->
            Chart(
                timeSpan,
                response.values.map { Chart.ChartValue(it.timeStamp, it.value) }
            )
        }
}
