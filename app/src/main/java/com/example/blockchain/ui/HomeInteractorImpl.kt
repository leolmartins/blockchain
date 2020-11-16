package com.example.blockchain.ui

import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.GetHomeDataUseCase

/**
 * @author Leonardo Martins on 15/11/20
 */
class HomeInteractorImpl(
    getHomeDataUseCase: GetHomeDataUseCase,
    fetchBlockChainStatsUseCase: FetchBlockChainStatsUseCase,
    fetchBlockChainChartUseCase: FetchBlockChainChartUseCase
) : HomeContract.Interactor,
    GetHomeDataUseCase by getHomeDataUseCase,
    FetchBlockChainStatsUseCase by fetchBlockChainStatsUseCase,
    FetchBlockChainChartUseCase by fetchBlockChainChartUseCase
