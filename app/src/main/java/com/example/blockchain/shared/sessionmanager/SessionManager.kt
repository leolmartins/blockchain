package com.example.blockchain.shared.sessionmanager

import kotlin.reflect.KClass

/**
 * @author Leonardo Martins on 15/11/20
 */
interface SessionManager {

    fun <T> save(key: String, data: T)

    fun <T: Any> get(key: String, type: KClass<T>): T?
}
