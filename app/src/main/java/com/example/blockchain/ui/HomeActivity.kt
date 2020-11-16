package com.example.blockchain.ui

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blockchain.R
import com.example.blockchain.base.BaseActivity
import com.example.blockchain.shared.model.ChartItemDisplay
import com.example.blockchain.shared.model.ChartTimeSpan
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_pb_loading
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_rv_chart
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_sp_timespan
import kotlinx.android.synthetic.main.home_activity_layout.home_activity_tv_price
import kotlinx.android.synthetic.main.home_activity_layout.home_bt_update_price
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View,
    AdapterView.OnItemSelectedListener {

    override val layoutRes = R.layout.home_activity_layout

    override val presenter: HomeContract.Presenter by inject { parametersOf(this) }

    private val chartAdapter: ChartAdapter by inject()

    override fun initialize() {
        supportActionBar?.setTitle(R.string.home_activity_tv_action_bar_title)

        home_bt_update_price.setOnClickListener { presenter.fetchBlockChainStats() }

        home_activity_rv_chart.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = chartAdapter
        }

        presenter.checkCurrentCache()
    }

    override fun showLoading() {
        home_bt_update_price.isVisible = false
        home_activity_pb_loading.isVisible = true
    }

    override fun hideLoading() {
        home_activity_pb_loading.isVisible = false
        home_bt_update_price.isVisible = true
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

    override fun fillChart(chartDisplay: List<ChartItemDisplay>) {
        chartAdapter.list = chartDisplay
    }

    override fun setupTimeSpan(currentTimeSpan: ChartTimeSpan) {
        home_activity_sp_timespan.apply {
            adapter = ArrayAdapter(
                this@HomeActivity,
                android.R.layout.simple_spinner_dropdown_item,
                ChartTimeSpan.values()
            )
            setSelection(currentTimeSpan.id)
            post { onItemSelectedListener = this@HomeActivity }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, p1: View?, position: Int, id: Long) {
        presenter.fetchChart(parent.getItemAtPosition(position) as ChartTimeSpan)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit
}
