package com.example.blockchain.ui

import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase

/**
 * @author Leonardo Martins on 15/11/20
 */
class HomeInteractorImpl(
    fetchBlockChainStatsUseCase: FetchBlockChainStatsUseCase,
    fetchBlockChainChartUseCase: FetchBlockChainChartUseCase
) : HomeContract.Interactor,
    FetchBlockChainStatsUseCase by fetchBlockChainStatsUseCase,
    FetchBlockChainChartUseCase by fetchBlockChainChartUseCase
