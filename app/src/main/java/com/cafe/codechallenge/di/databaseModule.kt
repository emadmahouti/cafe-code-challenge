package com.cafe.codechallenge.di

import androidx.room.Room
import com.cafe.codechallenge.data.local.util.AppDatabase
import org.koin.dsl.module

/**
 * Created by emadmahouti on 2/11/24
 */
val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "database-name"
        ).build()
    }


    single { get<AppDatabase>().movieDao() }
}