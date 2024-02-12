package com.cafe.codechallenge.di

import com.cafe.codechallenge.data.remote.interceptors.HeaderInterceptor
import com.cafe.codechallenge.data.remote.services.MovieApiService
import com.cafe.codechallenge.data.remote.util.NetworkRunner
import com.cafe.codechallenge.util.providers.ConfigProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by emadmahouti on 5/13/23
 */
val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<HeaderInterceptor>())
            .build()
    }

    single {
        val config = get<ConfigProvider>()

        Retrofit.Builder()
            .baseUrl(config.base_url)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory { NetworkRunner() }
    single { HeaderInterceptor(get()) }
    single { HttpLoggingInterceptor().also {
        it.level= HttpLoggingInterceptor.Level.BODY
    } }

    single { getApiService(get(), MovieApiService::class.java) }
}

internal fun <T>getApiService(retrofit : Retrofit, service : Class<T>): T {
    return retrofit.create(service)
}