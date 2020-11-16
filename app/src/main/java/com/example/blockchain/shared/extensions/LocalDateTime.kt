package com.example.blockchain.shared.extensions

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * @author Leonardo Martins on 15/11/20
 */
fun LocalDateTime.toString(format: String) = DateTimeFormatter.ofPattern(format).let(::format)
