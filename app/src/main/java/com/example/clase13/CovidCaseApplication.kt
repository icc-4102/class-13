package com.example.clase13

import android.app.Application
import com.example.clase13.modules.appModule
import com.example.clase13.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CovidCaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@CovidCaseApplication)
            // declare modules
            modules(listOf(appModule, networkModule))
        }
    }

}