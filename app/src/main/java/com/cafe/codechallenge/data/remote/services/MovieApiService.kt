package com.cafe.codechallenge.data.remote.services

import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by emadmahouti on 2/8/24
 */
interface MovieApiService {

    @GET("reference/movie-upcoming-list")
    suspend fun getIntroData(@Query("page") page: Int): Response<ItemsContainer<MovieResponse>>
}