package com.semyong.shakurotask

import android.app.Application
import com.semyong.shakurotask.di.apiModule
import com.semyong.shakurotask.di.dataModule
import com.semyong.shakurotask.di.errorManagerModule
import com.semyong.shakurotask.di.presentationModule
import com.semyong.shakurotask.di.repositoryModule
import com.semyong.shakurotask.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    presentationModule,
                    apiModule,
                    dataModule,
                    errorManagerModule,
                    useCaseModule,
                    repositoryModule
                )
            )
        }
    }
}