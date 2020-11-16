package com.example.blockchain

import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartResponse
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.model.SharedPreferencesKeys
import com.example.blockchain.shared.services.ChartsService
import com.example.blockchain.shared.sessionmanager.SessionManager
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

/**
 * @author Leonardo Martins on 16/11/20
 */
class FetchBlockChainChartUseCaseTest {

    private val sessionManager = mockk<SessionManager>()
    private val chartsService = mockk<ChartsService>()

    private lateinit var useCase: FetchBlockChainChartUseCase

    @Before
    fun setupTest() {
        useCase = FetchBlockChainChartUseCaseImpl(sessionManager, chartsService)
    }

    @Test
    fun `should return chart when successfully fetching chart`() {
        val response = getResponseMock()
        val chart = getChartMock()

        every { chartsService.fetchChart(any(), any()) } returns Single.just(response)
        every { sessionManager.save(any(), any<Chart>()) } just runs

        useCase.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK).test().assertResult(chart)

        verify(exactly = 1) {
            chartsService.fetchChart(ChartTimeSpan.ONE_WEEK.value, true)
            sessionManager.save(SharedPreferencesKeys.CHART_KEY, chart)
        }
        confirmVerified(sessionManager, chartsService)
    }

    private fun getResponseMock() = ChartResponse(
        "OK",
        "Market Price",
        "dolar",
        "day",
        "The market price on the current day",
        listOf(
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 8, 12, 0, 0),
                507.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 7, 12, 0, 0),
                506.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 6, 12, 0, 0),
                505.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 5, 12, 0, 0),
                504.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 4, 12, 0, 0),
                503.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 3, 12, 0, 0),
                502.0
            ),
            ChartResponse.ChartResponseValue(
                LocalDateTime.of(2020, 2, 2, 12, 0, 0),
                501.0
            )

        )
    )

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
