package com.example.blockchain.shared.extensions

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * @author Leonardo Martins on 15/11/20
 */
fun Double.formatAsDolar() = Locale.US.let { locale ->
    NumberFormat.getCurrencyInstance(locale)
        .apply {
            currency = Currency.getInstance(locale)
        }.format(this)
}
