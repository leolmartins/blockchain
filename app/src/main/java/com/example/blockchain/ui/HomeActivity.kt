package com.example.blockchain.ui

import android.widget.Toast
import com.example.blockchain.R
import com.example.blockchain.base.BaseActivity
import kotlinx.android.synthetic.main.home_activity_layout.home_bt_fetch_stats
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View {

    override val presenter: HomeContract.Presenter by inject { parametersOf(this) }

    override val layoutRes = R.layout.home_activity_layout

    override fun initialize() {
        supportActionBar?.setTitle(R.string.home_activity_tv_action_bar_title)

        home_bt_fetch_stats.setOnClickListener {
            presenter.fetchBlockChainStats()
        }
    }

    override fun showLoading() {
        // TODO
    }

    override fun hideLoading() {
        // TODO
    }

    override fun showToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
