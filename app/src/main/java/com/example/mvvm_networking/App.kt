package com.example.mvvm_networking

import android.app.Application
import com.example.mvvm_networking.di.UserModelModule
import com.example.mvvm_networking.di.appModule
import com.example.mvvm_networking.di.localDbModule
import com.example.mvvm_networking.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(UserModelModule, appModule, localDbModule, repoModule)
        }
    }
}