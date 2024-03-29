package com.cafe.codechallenge.data.local.repositories

import com.cafe.codechallenge.data.local.dao.MovieDao
import com.cafe.codechallenge.data.local.model.MovieLocalEntity

/**
 * Created by emadmahouti on 2/11/24
 */

interface PersistentMovieRepository {
    suspend fun getMovies(limit: Int, offset: Int): List<MovieLocalEntity>
    suspend fun count(): Int
    suspend fun insertMovies(items: List<MovieLocalEntity>)

    suspend fun delete(ids: List<Int>)
}


class PersistentMovieRepositoryImp(private val dao: MovieDao): PersistentMovieRepository {
    override suspend fun getMovies(limit: Int, offset: Int): List<MovieLocalEntity> {
        return dao.getAll(limit, offset)
    }

    override suspend fun count(): Int {
        return dao.count()
    }

    override suspend fun insertMovies(items: List<MovieLocalEntity>) {
        dao.insert(items)
    }

    override suspend fun delete(ids: List<Int>) {
        dao.delete(ids)
    }
}