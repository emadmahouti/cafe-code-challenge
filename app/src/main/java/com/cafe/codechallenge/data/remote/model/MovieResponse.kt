package com.cafe.codechallenge.data.remote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cafe.codechallenge.util.movieTable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by emadmahouti on 2/8/24
 */

@JsonClass(generateAdapter = true)
@Entity(movieTable)
data class MovieResponse (
    @PrimaryKey
    @field:Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "original_title")
    @field:Json(name = "original_title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    @field:Json(name = "poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "overview")
    @field:Json(name = "overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    @field:Json(name = "popularity")
    val popularity: Float,

    @ColumnInfo(name = "release_date")
    @field:Json(name = "release_date")
    val release_date: String,
)