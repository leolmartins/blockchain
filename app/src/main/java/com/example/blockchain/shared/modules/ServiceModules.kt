package com.example.blockchain.shared.modules

import com.example.blockchain.shared.modules.NetworkModules.BLOCK_CHAIN_API
import com.example.blockchain.shared.services.StatsService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Leonardo Martins on 15/11/20
 */
object ServiceModules {

    val modules = module {
        factory { get<Retrofit>(named(BLOCK_CHAIN_API)).create(StatsService::class.java) }
    }
}
