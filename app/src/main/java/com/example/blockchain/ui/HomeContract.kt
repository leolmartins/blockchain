package com.example.blockchain.ui

import com.example.blockchain.base.BaseContract
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase

/**
 * @author Leonardo Martins on 15/11/20
 */
interface HomeContract : BaseContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showErrorToast()

        fun fillMarketPrice(price: String)
    }

    interface Presenter : BaseContract.Presenter {

        fun fetchBlockChainStats()

        fun fetchChart()
    }

    interface Interactor : BaseContract.Interactor, FetchBlockChainStatsUseCase,
        FetchBlockChainChartUseCase
}
