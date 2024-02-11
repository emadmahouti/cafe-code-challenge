package com.cafe.codechallenge.domain.model

import androidx.annotation.Keep

/**
 * Created by emadmahouti on 2/11/24
 */
@Keep
data class MovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val popularity: Float,
    val releaseDate: String,
)