package com.example.blockchain.shared.modules

import com.example.blockchain.ui.HomeModule
import org.koin.dsl.module

/**
 * @author Leonardo Martins on 15/11/20
 */
object UiModules {

    val modules = module {
        HomeModule.module
    }
}
