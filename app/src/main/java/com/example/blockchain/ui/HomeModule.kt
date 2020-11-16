package com.example.blockchain.ui

import org.koin.dsl.module

/**
 * @author Leonardo Martins on 15/11/20
 */
object HomeModule {

    val module = module {
        factory<HomeContract.Presenter> { (view: HomeContract.View) ->
            HomePresenterImpl(
                view,
                HomeInteractorImpl(get(), get()),
                get(),
                get()
            )
        }
    }
}
