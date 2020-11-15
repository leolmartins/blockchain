package com.example.blockchain.ui

import com.example.blockchain.R
import com.example.blockchain.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View {

    override val presenter: HomeContract.Presenter by inject { parametersOf(this)}

    override val layoutRes = R.layout.activity_main

    override fun initialize() {
        // TODO
    }
}
