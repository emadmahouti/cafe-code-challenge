package com.cafe.codechallenge.di

import com.cafe.codechallenge.util.providers.ConfigProvider
import com.cafe.codechallenge.util.providers.HeaderProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

/**
 * Created by emadmahouti on 5/13/23
 */

val appModule = module {

    factory { ConfigProvider() }
    factory { HeaderProvider(get()) }

    single {
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}