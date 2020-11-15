package com.example.blockchain.shared.modules

import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Leonardo Martins on 15/11/20
 */
object NetworkModules {

    private const val BLOCK_CHAIN_API = "Block Chain API"
    private const val NETWORK_TIMEOUT = 30L

    val modules = module {
        single(named(BLOCK_CHAIN_API)) {
            OkHttpClient.Builder()
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build().let {client ->
                    Retrofit.Builder()
                        .baseUrl("https://api.blockchain.info/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(get()))
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                }
        }
    }
}
