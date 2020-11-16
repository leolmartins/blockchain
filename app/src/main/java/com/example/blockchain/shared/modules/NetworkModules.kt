package com.example.blockchain.shared.modules

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.DateTimeException
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * @author Leonardo Martins on 15/11/20
 */
object NetworkModules {

    const val BLOCK_CHAIN_API = "Block Chain API"
    private const val NETWORK_TIMEOUT = 30L

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val modules = module {
        single(named(BLOCK_CHAIN_API)) {
            OkHttpClient.Builder()
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build().let { client ->
                    Retrofit.Builder()
                        .baseUrl("https://api.blockchain.info/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(get()))
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                }
        }
    }

    class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocalDateTime {
            json?.toString()?.toLong()?.let { unixDate ->
                try {
                    return LocalDateTime.ofEpochSecond(unixDate, 0, ZoneOffset.UTC)
                } catch (exception: DateTimeException) {
                    Timber.d("$unixDate could not be parsed to LocalDateTime.")
                }
            }
            throw JsonParseException(
                "LocalDateTime could not be parsed."
            )
        }
    }
}
