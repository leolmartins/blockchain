package com.example.blockchain.ui

import com.example.blockchain.base.BaseContract
import com.example.blockchain.shared.model.ChartItemDisplay
import com.example.blockchain.shared.model.ChartTimeSpan
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.GetHomeDataUseCase

/**
 * @author Leonardo Martins on 15/11/20
 */
interface HomeContract : BaseContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showErrorToast()

        fun fillMarketPrice(price: String)

        fun fillChart(chartDisplay: List<ChartItemDisplay>)

        fun setupTimeSpan(currentTimeSpan: ChartTimeSpan)
    }

    interface Presenter : BaseContract.Presenter {

        fun checkCurrentCache()

        fun fetchBlockChainStats()

        fun fetchChart(timeSpan: ChartTimeSpan)
    }

    interface Interactor : BaseContract.Interactor, GetHomeDataUseCase, FetchBlockChainStatsUseCase,
        FetchBlockChainChartUseCase
}
