package com.cafe.codechallenge.data.remote.repositories

import com.cafe.codechallenge.data.model.DataHolder
import com.cafe.codechallenge.data.model.ItemsContainer
import com.cafe.codechallenge.data.remote.services.MovieApiService
import com.cafe.codechallenge.data.remote.util.NetworkRunner
import com.cafe.codechallenge.domain.model.MovieEntity

/**
 * Created by emadmahouti on 2/8/24
 */
interface MovieRepository {
    suspend fun getMovies(page: Int): DataHolder<ItemsContainer<MovieEntity>>
}

class MovieRepositoryImp(private val apiService: MovieApiService, private val networkRunner: NetworkRunner): MovieRepository {
    override suspend fun getMovies(page: Int): DataHolder<ItemsContainer<MovieEntity>> {
        return networkRunner.performCall { apiService.getIntroData(page) }
    }
}