package com.cafe.codechallenge.data.local.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cafe.codechallenge.data.model.MappableModel
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.util.movieTable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by emadmahouti on 2/11/24
 */
@Keep
@JsonClass(generateAdapter = true)
@Entity(movieTable)
data class MovieLocalEntity (
    @PrimaryKey
    @field:Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "original_title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    val popularity: Float,

    @ColumnInfo(name = "release_date")
    val release_date: String,
): MappableModel<MovieEntity> {
    override fun map(): MovieEntity {
        return MovieEntity(
            id,
            title,
            poster_path,
            overview,
            popularity,
            release_date
        )
    }
}