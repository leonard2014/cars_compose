package com.leonard.cars_compose

import android.app.Application
import com.leonard.cars_compose.injection.appModule
import com.leonard.cars_compose.injection.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CarsApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}