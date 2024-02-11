package com.cafe.codechallenge.domain.usecases

import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.model.DataHolder
import com.cafe.codechallenge.data.model.ItemsContainer
import com.cafe.codechallenge.data.model.MappableItemContainer
import com.cafe.codechallenge.data.model.doOnSuccess
import com.cafe.codechallenge.data.remote.repositories.MovieRepository
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.util.diff
import com.cafe.codechallenge.util.toLocalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by emadmahouti on 2/11/24
 */

interface GetMovieUseCase : UseCase {
    suspend operator fun invoke(pageCursor: Int, perPage: Int = 20): DataHolder<ItemsContainer<MovieEntity>>
}

class GetMovieUseCaseImp(
    private val movieRepository: MovieRepository,
    private val persistentRepository: PersistentMovieRepository,
) : GetMovieUseCase {
    override suspend fun invoke(pageCursor: Int, perPage: Int): DataHolder<ItemsContainer<MovieEntity>> {
        return withContext(Dispatchers.IO) {
            val results = movieRepository.getMovies(pageCursor)
            results.doOnSuccess { movies ->
                val localMovies = persistentRepository.getMovies(perPage, perPage * (pageCursor - 1))

                // left must insert and right must remove
                val diff = movies.results diff localMovies.map { it.map() }
                if (diff.second.isNotEmpty())
                    persistentRepository.delete(diff.second.map { it.id })
                if (diff.first.isNotEmpty())
                    persistentRepository.insertMovies(diff.first.map { it.toLocalEntity() })
            }
        }
    }
}