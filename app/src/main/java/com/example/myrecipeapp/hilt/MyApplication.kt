package com.example.myrecipeapp.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.GlobalContext.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}