package com.cafe.codechallenge.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by emadmahouti on 2/8/24
 */
@JsonClass(generateAdapter = true)
data class MovieResponse (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val title: String,
    @field:Json(name = "poster_path") val poster_path: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "release_date") val release_date: String,
)