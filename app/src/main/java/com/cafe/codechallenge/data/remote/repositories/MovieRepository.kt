package com.cafe.codechallenge.data.remote.repositories

import com.cafe.codechallenge.data.remote.model.DataHolder
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.data.remote.services.MovieApiService
import com.cafe.codechallenge.data.remote.util.NetworkRunner

/**
 * Created by emadmahouti on 2/8/24
 */
interface MovieRepository {
    suspend fun getMovies(page: Int): DataHolder<ItemsContainer<MovieResponse>>
}

class MovieRepositoryImp(private val apiService: MovieApiService, private val networkRunner: NetworkRunner): MovieRepository {
    override suspend fun getMovies(page: Int): DataHolder<ItemsContainer<MovieResponse>> {
        return networkRunner.performCall { apiService.getIntroData(page) }
    }
}