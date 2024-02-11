package com.cafe.codechallenge

import android.app.Application
import com.cafe.codechallenge.di.*
import com.cafe.codechallenge.util.providers.StyleProvider
import com.pixy.codebase.Codebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by emadmahouti on 2/8/24
 */
class TaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Codebase.init(StyleProvider.Default())
        startDI()
    }

    private fun startDI() {
        startKoin {
            androidContext(this@TaskApplication)
            modules(appModule, networkModule, repositoryModule, useCaseModule, viewModelModule, databaseModule)
        }
    }
}