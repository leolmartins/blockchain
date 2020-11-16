package com.example.blockchain

import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.model.HomeData
import com.example.blockchain.shared.model.SharedPreferencesKeys
import com.example.blockchain.shared.sessionmanager.SessionManager
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.GetHomeDataUseCase
import com.example.blockchain.shared.usecases.GetHomeDataUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

/**
 * @author Leonardo Martins on 16/11/20
 */
class GetHomeDataUseCaseTest {

    private val sessionManager = mockk<SessionManager>()
    private val fetchBlockChainStatsUseCase = mockk<FetchBlockChainStatsUseCase>()
    private val fetchBlockChainChartUseCase = mockk<FetchBlockChainChartUseCase>()

    private lateinit var useCase: GetHomeDataUseCase

    @Before
    fun setupTest() {
        useCase = GetHomeDataUseCaseImpl(
            sessionManager,
            fetchBlockChainStatsUseCase,
            fetchBlockChainChartUseCase
        )
    }

    @Test
    fun `should return home data when information exists in cache`() {
        val stats = BlockChainStats(500.0)
        val chart = getChartMock()
        val homeData = HomeData(stats, chart)

        every {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
        } returns stats
        every {
            sessionManager.get(SharedPreferencesKeys.CHART_KEY, Chart::class)
        } returns chart

        useCase.getHomeData().test().assertResult(homeData)

        verify(exactly = 1) {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
            sessionManager.get(SharedPreferencesKeys.CHART_KEY, Chart::class)
        }
        confirmVerified(sessionManager, fetchBlockChainStatsUseCase, fetchBlockChainChartUseCase)
    }

    @Test
    fun `should fetch stats and chart when stats do not exist in cache and then return home data successfully`() {
        val stats = BlockChainStats(500.0)
        val chart = getChartMock()
        val homeData = HomeData(stats, chart)

        every {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
        } returns null
        every { fetchBlockChainStatsUseCase.fetchBlockChainStats() } returns Single.just(stats)
        every { fetchBlockChainChartUseCase.fetchBlockChainChart(any()) } returns Single.just(chart)

        useCase.getHomeData().test().assertResult(homeData)

        verify(exactly = 1) {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
            fetchBlockChainStatsUseCase.fetchBlockChainStats()
            fetchBlockChainChartUseCase.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK)
        }
        confirmVerified(sessionManager, fetchBlockChainStatsUseCase, fetchBlockChainChartUseCase)
    }

    @Test
    fun `should fetch stats and chart when chart does not exist in cache and then return home data successfully`() {
        val stats = BlockChainStats(500.0)
        val chart = getChartMock()
        val homeData = HomeData(stats, chart)

        every {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
        } returns stats
        every {
            sessionManager.get(SharedPreferencesKeys.CHART_KEY, Chart::class)
        } returns null
        every { fetchBlockChainStatsUseCase.fetchBlockChainStats() } returns Single.just(stats)
        every { fetchBlockChainChartUseCase.fetchBlockChainChart(any()) } returns Single.just(chart)

        useCase.getHomeData().test().assertResult(homeData)

        verify(exactly = 1) {
            sessionManager.get(SharedPreferencesKeys.STATS_KEY, BlockChainStats::class)
            sessionManager.get(SharedPreferencesKeys.CHART_KEY, Chart::class)
            fetchBlockChainStatsUseCase.fetchBlockChainStats()
            fetchBlockChainChartUseCase.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK)
        }
        confirmVerified(sessionManager, fetchBlockChainStatsUseCase, fetchBlockChainChartUseCase)
    }

    private fun getChartMock() = Chart(
        ChartTimeSpan.ONE_WEEK,
        listOf(
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 8, 12, 0, 0),
                507.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 7, 12, 0, 0),
                506.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 6, 12, 0, 0),
                505.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 5, 12, 0, 0),
                504.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 4, 12, 0, 0),
                503.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 3, 12, 0, 0),
                502.0
            ),
            Chart.ChartValue(
                LocalDateTime.of(2020, 2, 2, 12, 0, 0),
                501.0
            )
        )
    )
}
