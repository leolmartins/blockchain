package com.example.blockchain.shared.modules

import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainChartUseCaseImpl
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCaseImpl
import com.example.blockchain.shared.usecases.GetHomeDataUseCase
import com.example.blockchain.shared.usecases.GetHomeDataUseCaseImpl
import org.koin.dsl.module

/**
 * @author Leonardo Martins on 15/11/20
 */
object UseCaseModules {

    val modules = module {
        factory<GetHomeDataUseCase> {
            GetHomeDataUseCaseImpl(
                get(),
                get(),
                get()
            )
        }
        factory<FetchBlockChainStatsUseCase> {
            FetchBlockChainStatsUseCaseImpl(
                get(),
                get()
            )
        }
        factory<FetchBlockChainChartUseCase> {
            FetchBlockChainChartUseCaseImpl(
                get(),
                get()
            )
        }
    }
}
