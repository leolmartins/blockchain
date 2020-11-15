package com.example.blockchain.shared.modules

import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCaseImpl
import org.koin.dsl.module

/**
 * @author Leonardo Martins on 15/11/20
 */
object UseCaseModules {

    val modules = module {
        factory<FetchBlockChainStatsUseCase> { FetchBlockChainStatsUseCaseImpl(get()) }
    }
}
