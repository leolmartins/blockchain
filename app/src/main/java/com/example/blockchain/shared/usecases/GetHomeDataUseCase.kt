package com.example.blockchain.shared.usecases

import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.model.HomeData
import com.example.blockchain.shared.model.SharedPreferencesKeys
import com.example.blockchain.shared.sessionmanager.SessionManager
import io.reactivex.rxjava3.core.Single

/**
 * @author Leonardo Martins on 15/11/20
 */
interface GetHomeDataUseCase {

    fun getHomeData(): Single<HomeData>
}

class GetHomeDataUseCaseImpl(
    private val sessionManager: SessionManager,
    private val fetchBlockChainStatsUseCase: FetchBlockChainStatsUseCase,
    private val fetchBlockChainChartUseCase: FetchBlockChainChartUseCase
) : GetHomeDataUseCase {

    override fun getHomeData(): Single<HomeData> {
        val blockChainStats = getCachedBlockChainStats() ?: return fetchAndSaveHomeData()
        val chart = getCachedChart() ?: return fetchAndSaveHomeData()

        return Single.just(HomeData(blockChainStats, chart))
    }

    private fun fetchAndSaveHomeData() =
        fetchBlockChainStatsUseCase.fetchBlockChainStats().flatMap { stats ->
            fetchBlockChainChartUseCase.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK).map { chart ->
                HomeData(stats, chart)
            }
        }

    private fun getCachedBlockChainStats() =
        sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)

    private fun getCachedChart() =
        sessionManager.get(SharedPreferencesKeys.CHART_KEY, Chart::class)
}
