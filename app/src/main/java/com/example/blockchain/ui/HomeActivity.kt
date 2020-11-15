package com.example.blockchain.ui

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.blockchain.R
import com.example.blockchain.base.BaseActivity
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_pb_loading
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_tv_price
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
        home_activity_pb_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        home_activity_pb_loading.visibility = View.INVISIBLE
    }

    override fun fillMarketPrice(price: String) {
        home_activity_tv_price.text = price
    }

    override fun showErrorToast() {
        Toast.makeText(
            this,
            getString(R.string.home_activity_tv_error_toast),
            Toast.LENGTH_LONG
        ).show()
    }
}
