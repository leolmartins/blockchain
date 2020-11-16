package com.example.blockchain.shared.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

/**
 * @author Leonardo Martins on 15/11/20
 */
data class ChartResponse(
    @SerializedName("status") val status: String,
    @SerializedName("name") val name: String,
    @SerializedName("unit") val unit: String,
    @SerializedName("period") val period: String,
    @SerializedName("description") val description: String,
    @SerializedName("values") val values: List<ChartResponseValue>
) {

    data class ChartResponseValue(
        @SerializedName("x") val timeStamp: LocalDateTime,
        @SerializedName("y") val value: Double
    )
}
