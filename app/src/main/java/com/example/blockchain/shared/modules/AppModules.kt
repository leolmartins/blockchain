package com.example.blockchain.shared.modules

import com.example.blockchain.shared.providers.AppDisposableProvider
import com.example.blockchain.shared.providers.AppSchedulerProvider
import com.example.blockchain.shared.providers.DisposableProvider
import com.example.blockchain.shared.providers.SchedulerProvider
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime

/**
 * @author Leonardo Martins on 15/11/20
 */
object AppModules {

    private val providersModule = module {
        single {
            GsonBuilder()
                .setLenient()
                .registerTypeAdapter(
                    LocalDateTime::class.java,
                    NetworkModules.LocalDateTimeDeserializer()
                )
                .create()
        }
        factory<DisposableProvider> { AppDisposableProvider() }
        factory<SchedulerProvider> { AppSchedulerProvider() }
    }

    val modules = mutableListOf(
        providersModule,
        NetworkModules.modules,
        ServiceModules.modules,
        UseCaseModules.modules,
    ).apply {
        addAll(UiModules.modules)
    }


}
