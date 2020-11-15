package com.example.blockchain.base

import android.app.Application
import com.example.blockchain.shared.modules.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * @author Leonardo Martins on 15/11/20
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(AppModules.modules)
        }
    }
}
