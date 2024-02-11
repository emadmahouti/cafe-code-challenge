package com.cafe.codechallenge.data.local.repositories

import com.cafe.codechallenge.data.local.dao.MovieDao
import com.cafe.codechallenge.data.remote.model.MovieResponse

/**
 * Created by emadmahouti on 2/11/24
 */

interface PersistentMovieRepository {
    suspend fun getMovies(limit: Int, offset: Int): List<MovieResponse>
    suspend fun count(): Int
    suspend fun insertMovies(items: List<MovieResponse>)
}


class PersistentMovieRepositoryImp(private val dao: MovieDao): PersistentMovieRepository {
    override suspend fun getMovies(limit: Int, offset: Int): List<MovieResponse> {
        return dao.getAll(limit, offset)
    }

    override suspend fun count(): Int {
        return dao.count()
    }

    override suspend fun insertMovies(items: List<MovieResponse>) {
        dao.insert(items)
    }
}