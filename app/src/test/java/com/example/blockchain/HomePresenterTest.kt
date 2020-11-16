package com.example.blockchain

import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartItemDisplay
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.model.HomeData
import com.example.blockchain.shared.providers.AppDisposableProvider
import com.example.blockchain.ui.HomeContract
import com.example.blockchain.ui.HomePresenterImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

class HomePresenterTest {

    private val view = mockk<HomeContract.View>()
    private val interactor = mockk<HomeContract.Interactor>()

    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: HomeContract.Presenter

    @Before
    fun setupTest() {
        testScheduler = TestScheduler()
        presenter = HomePresenterImpl(
            view,
            interactor,
            AppDisposableProvider(),
            TestSchedulerProvider(testScheduler)
        )
    }

    @Test
    fun `show error toast when failing to check current cache`() {
        val error = IllegalArgumentException()

        every { interactor.getHomeData() } returns Single.error(error)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.showErrorToast() } just runs

        presenter.checkCurrentCache()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.getHomeData()
            view.showLoading()
            view.hideLoading()
            view.showErrorToast()
        }
        confirmVerified(view, interactor)
    }

    @Test
    fun `fill market price, fill chart and setup time span when successfully retrieving home data`() {
        val homeData = HomeData(
            BlockChainStats(500.0),
            getChartMock()
        )
        val chartDisplay = getChartDisplayMock()

        every { interactor.getHomeData() } returns Single.just(homeData)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.setupTimeSpan(any()) } just runs
        every { view.fillMarketPrice(any()) } just runs
        every { view.fillChart(any()) } just runs

        presenter.checkCurrentCache()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.getHomeData()
            view.showLoading()
            view.hideLoading()
            view.setupTimeSpan(ChartTimeSpan.ONE_WEEK)
            view.fillMarketPrice("$500.00")
            view.fillChart(chartDisplay)
        }
        confirmVerified(view, interactor)
    }

    @Test
    fun `show error toast when failing to fetch block chain stats`() {
        val error = IllegalArgumentException()

        every { interactor.fetchBlockChainStats() } returns Single.error(error)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.showErrorToast() } just runs

        presenter.fetchBlockChainStats()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchBlockChainStats()
            view.showLoading()
            view.hideLoading()
            view.showErrorToast()
        }
        confirmVerified(view, interactor)
    }

    @Test
    fun `fill block market price when successfully fetching block chain stats`() {
        val stats = BlockChainStats(500.0)

        every { interactor.fetchBlockChainStats() } returns Single.just(stats)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.fillMarketPrice(any()) } just runs

        presenter.fetchBlockChainStats()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchBlockChainStats()
            view.showLoading()
            view.hideLoading()
            view.fillMarketPrice("$500.00")
        }
        confirmVerified(view, interactor)
    }

    @Test
    fun `show error toast when failing to fetch chart`() {
        val error = IllegalArgumentException()

        every { interactor.fetchBlockChainChart(any()) } returns Single.error(error)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.showErrorToast() } just runs

        presenter.fetchChart(ChartTimeSpan.ONE_WEEK)
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK)
            view.showLoading()
            view.hideLoading()
            view.showErrorToast()
        }
        confirmVerified(view, interactor)
    }

    @Test
    fun `fill chart when successfully fetching chart`() {
        val chart = getChartMock()
        val chartDisplay = getChartDisplayMock()

        every { interactor.fetchBlockChainChart(any()) } returns Single.just(chart)
        every { view.showLoading() } just runs
        every { view.hideLoading() } just runs
        every { view.fillChart(any()) } just runs

        presenter.fetchChart(ChartTimeSpan.ONE_WEEK)
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchBlockChainChart(ChartTimeSpan.ONE_WEEK)
            view.showLoading()
            view.hideLoading()
            view.fillChart(chartDisplay)
        }
        confirmVerified(view, interactor)
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

    private fun getChartDisplayMock() = listOf(
        ChartItemDisplay(
            "$501.00",
            "02/02/2020"
        ),
        ChartItemDisplay(
            "$502.00",
            "02/03/2020"
        ),
        ChartItemDisplay(
            "$503.00",
            "02/04/2020"
        ),
        ChartItemDisplay(
            "$504.00",
            "02/05/2020"
        ),
        ChartItemDisplay(
            "$505.00",
            "02/06/2020"
        ),
        ChartItemDisplay(
            "$506.00",
            "02/07/2020"
        ),
        ChartItemDisplay(
            "$507.00",
            "02/08/2020"
        )
    )
}
