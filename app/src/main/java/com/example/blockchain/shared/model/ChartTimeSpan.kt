package com.example.blockchain.shared.model

/**
 * @author Leonardo Martins on 15/11/20
 */
enum class ChartTimeSpan(
    val id: Int,
    val value: String,
    val description: String
) {
    ONE_WEEK(0, "1week", "One Week"),
    ONE_MONTH(1, "1month", "One Month"),
    TWO_MONTHS(2, "2months", "Two Months"),
    SIX_MONTHS(3, "6months", "Six Months"),
    ONE_YEAR(4, "1year", "One Year"),
    THREE_YEARS(5, "3years", "Three Years"),
}
