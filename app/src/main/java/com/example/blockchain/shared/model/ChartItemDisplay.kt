package com.example.blockchain.shared.model

import com.example.blockchain.shared.extensions.formatAsDolar
import com.example.blockchain.shared.extensions.toString

/**
 * @author Leonardo Martins on 15/11/20
 */
data class ChartItemDisplay(
    val value: String,
    val date: String
) {
    constructor(chartValue: Chart.ChartValue) : this(
        chartValue.value.formatAsDolar(),
        chartValue.timeStamp.toString(DATE_FORMAT)
    )

    companion object {
        private const val DATE_FORMAT = "MM/dd/yyyy"
    }
}
