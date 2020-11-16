package com.example.blockchain.shared.model

import org.threeten.bp.LocalDateTime

/**
 * @author Leonardo Martins on 15/11/20
 */
data class Chart(
    val timeSpan: ChartTimeSpan,
    val values: List<ChartValue>
) {

    data class ChartValue(
        val timeStamp: LocalDateTime,
        val value: Double
    ) {
        constructor(valueResponse: ChartResponse.ChartResponseValue) : this(
            valueResponse.timeStamp,
            valueResponse.value
        )
    }
}
