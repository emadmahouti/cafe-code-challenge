package com.cafe.codechallenge.data.remote.repositories

import com.cafe.codechallenge.data.remote.model.DataHolder
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.data.remote.services.MovieApiService
import com.cafe.codechallenge.data.remote.util.NetworkRunner

/**
 * Created by emadmahouti on 2/8/24
 */
interface MainRepository {
    suspend fun getIntroData(page: Int): DataHolder<ItemsContainer<MovieResponse>>
}

class MainRepositoryImp(private val apiService: MovieApiService, private val networkRunner: NetworkRunner): MainRepository {
    override suspend fun getIntroData(page: Int): DataHolder<ItemsContainer<MovieResponse>> {
        return networkRunner.performCall { apiService.getIntroData(page) }
    }
}