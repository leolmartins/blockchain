package com.example.blockchain.shared.sessionmanager

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlin.reflect.KClass

/**
 * @author Leonardo Martins on 15/11/20
 */
class AppSessionManager(
    private val sharedPreferences: SharedPreferences
) : SessionManager {

    override fun <T> save(key: String, data: T) {
        val editor = sharedPreferences.edit()
        editor.putString(key, Gson().toJson(data))
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String, type: KClass<T>): T? =
        sharedPreferences.getString(key, null)?.let { Gson().fromJson(it, type.java) as T }
}
