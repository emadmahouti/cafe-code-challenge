package com.cafe.codechallenge.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by emadmahouti on 2/11/24
 */
data class MovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val popularity: Float,
    val releaseDate: String,
)