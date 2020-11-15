package com.example.blockchain.shared.modules

import com.google.gson.GsonBuilder
import org.koin.dsl.module

/**
 * @author Leonardo Martins on 15/11/20
 */
object AppModules {

    private val gsonModule = module {
        single {
            GsonBuilder()
                .setLenient()
                .create()
        }
    }

    val modules = listOf(
        gsonModule,
        NetworkModules.modules,
        ServiceModules.modules,
        UseCaseModules.modules,
        UiModules.modules
    )
}
