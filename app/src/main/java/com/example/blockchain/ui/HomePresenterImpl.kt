package com.example.blockchain.ui

import com.example.blockchain.base.BasePresenter
import com.example.blockchain.shared.extensions.addTo
import com.example.blockchain.shared.extensions.formatAsDolar
import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.model.Chart
import com.example.blockchain.shared.model.ChartItemDisplay
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.providers.DisposableProvider
import com.example.blockchain.shared.providers.SchedulerProvider

/**
 * @author Leonardo Martins on 15/11/20
 */
class HomePresenterImpl(
    view: HomeContract.View,
    interactor: HomeContract.Interactor,
    disposableProvider: DisposableProvider,
    schedulerProvider: SchedulerProvider
) : BasePresenter<HomeContract.View, HomeContract.Interactor>(
    view,
    interactor,
    disposableProvider,
    schedulerProvider
), HomeContract.Presenter {

    override fun checkCurrentCache() {
        interactor.getHomeData()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                view.setupTimeSpan(it.chart.timeSpan)
                onFetchStatsSuccess(it.stats)
                onFetchChartSuccess(it.chart)

            }, { view.showErrorToast() })
            .addTo(disposableProvider)
    }

    override fun fetchBlockChainStats() {
        interactor.fetchBlockChainStats()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe(::onFetchStatsSuccess) { view.showErrorToast() }
            .addTo(disposableProvider)
    }

    override fun fetchChart(timeSpan: ChartTimeSpan) {
        interactor.fetchBlockChainChart(timeSpan)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe(::onFetchChartSuccess) { view.showErrorToast() }
            .addTo(disposableProvider)
    }

    private fun onFetchStatsSuccess(stats: BlockChainStats) {
        view.fillMarketPrice(stats.marketPriceUsd.formatAsDolar())
    }

    private fun onFetchChartSuccess(chart: Chart) {
        view.fillChart(chart.values.map(::ChartItemDisplay).reversed())
    }
}
