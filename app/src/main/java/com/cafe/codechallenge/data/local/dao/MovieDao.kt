package com.cafe.codechallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cafe.codechallenge.data.local.model.MovieLocalEntity
import com.cafe.codechallenge.util.movieTable

/**
 * Created by emadmahouti on 2/11/24
 */

@Dao
interface MovieDao {
    @Query("SELECT * FROM $movieTable ORDER BY popularity DESC LIMIT (:limit) OFFSET (:offset)" )
    suspend fun getAll(limit: Int, offset: Int): List<MovieLocalEntity>

    @Query("SELECT count(*) FROM $movieTable")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(items: List<MovieLocalEntity>)

    @Query("delete from $movieTable where id in (:ids)")
    suspend fun delete(ids: List<Int>)
}