package com.example.blockchain.ui

import com.example.blockchain.base.BasePresenter
import com.example.blockchain.shared.extensions.addTo
import com.example.blockchain.shared.extensions.formatAsDolar
import com.example.blockchain.shared.extensions.toString
import com.example.blockchain.shared.model.ChartItemDisplay
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.providers.DisposableProvider
import com.example.blockchain.shared.providers.SchedulerProvider
import timber.log.Timber

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

    private var currentTimeSpan: ChartTimeSpan = ChartTimeSpan.ONE_MONTH

    override fun fetchBlockChainStats() {
        interactor.fetchBlockChainStats()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe(
                { view.fillMarketPrice(it.marketPriceUsd.formatAsDolar()) },
                { view.showErrorToast() }
            )
            .addTo(disposableProvider)
    }

    override fun fetchChart(timeSpan: ChartTimeSpan) {
        interactor.fetchBlockChainChart(timeSpan)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe(
                { chart ->
                    currentTimeSpan = chart.timeSpan
                    view.fillChart(chart.values.map(::ChartItemDisplay))
                },
                {
                    Timber.d(it)
                    view.showErrorToast()
                }
            )
            .addTo(disposableProvider)
    }

    override fun fetchChart() {
        fetchChart(currentTimeSpan)
    }
}
