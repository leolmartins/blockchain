package com.example.blockchain.shared.modules

import android.content.Context
import com.example.blockchain.BuildConfig
import com.example.blockchain.shared.providers.AppDisposableProvider
import com.example.blockchain.shared.providers.AppSchedulerProvider
import com.example.blockchain.shared.providers.DisposableProvider
import com.example.blockchain.shared.providers.SchedulerProvider
import com.example.blockchain.shared.sessionmanager.AppSessionManager
import com.example.blockchain.shared.sessionmanager.SessionManager
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
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

    private val sessionManagerModule = module {
        single<SessionManager> {
            AppSessionManager(
                androidContext().getSharedPreferences(
                    BuildConfig.APPLICATION_ID,
                    Context.MODE_PRIVATE
                )
            )
        }
    }

    val modules = mutableListOf(
        providersModule,
        sessionManagerModule,
        NetworkModules.modules,
        ServiceModules.modules,
        UseCaseModules.modules,
    ).apply {
        addAll(UiModules.modules)
    }


}
