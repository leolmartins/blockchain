package com.example.blockchain.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.example.blockchain.R
import com.example.blockchain.shared.model.ChartItemDisplay
import kotlinx.android.synthetic.main.home_activity_chart_item_layout.view.home_activity_chart_item_tv_date
import kotlinx.android.synthetic.main.home_activity_chart_item_layout.view.home_activity_chart_item_tv_value

/**
 * @author Leonardo Martins on 15/11/20
 */
class ChartAdapter : RecyclerView.Adapter<ChartAdapter.ChartItemViewHolder>() {

    var list = listOf<ChartItemDisplay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ChartItemViewHolder, position: Int) {
        holder.itemView.apply {
            home_activity_chart_item_tv_value.text = list[position].value
            home_activity_chart_item_tv_date.text = list[position].date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChartItemViewHolder(inflate(parent))

    private fun inflate(@Nullable parent: ViewGroup) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.home_activity_chart_item_layout, parent, false) as View

    inner class ChartItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
}
