package com.cafe.codechallenge.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * Created by emadmahouti on 2/8/24
 */
@JsonClass(generateAdapter = true)
data class MovieResponse (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val title: String,
    @field:Json(name = "poster_path") val poster: String = "https://picsum.photos/400?random={${Random()}}", //TODO
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "release_date") val release_date: String,
)