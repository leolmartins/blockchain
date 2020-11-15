package com.example.blockchain.shared.model

import com.google.gson.annotations.SerializedName

/**
 * @author Leonardo Martins on 15/11/20
 */
data class BlockChainStatsResponse(
    @SerializedName("market_price_usd") val marketPriceUsd: Double
)
