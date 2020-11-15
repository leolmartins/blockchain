package com.example.blockchain.ui

import com.example.blockchain.base.BasePresenter
import com.example.blockchain.shared.extensions.addTo
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

    override fun fetchBlockChainStats() {
        interactor.fetchBlockChainStats()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe(
                {
                    Timber.i("Market price: ${it.marketPriceUsd}")
                },
                {
                    Timber.i(it)
                }
            )
            .addTo(disposableProvider)
    }
}
