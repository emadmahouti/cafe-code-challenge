package com.cafe.codechallenge.data.local.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cafe.codechallenge.data.local.dao.MovieDao
import com.cafe.codechallenge.data.local.model.MovieLocalEntity

/**
 * Created by emadmahouti on 2/11/24
 */
@Database(entities = [MovieLocalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}