package com.cafe.codechallenge.domain.usecases

import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.remote.model.DataHolder
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.data.remote.model.doOnSuccess
import com.cafe.codechallenge.data.remote.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by emadmahouti on 2/11/24
 */

interface GetMovieUseCase: UseCase {
    suspend operator fun invoke(pageCursor: Int): DataHolder<ItemsContainer<MovieResponse>>
}

class GetMovieUseCaseImp(
    private val movieRepository: MovieRepository,
    private val persistentRepository: PersistentMovieRepository,
) : GetMovieUseCase {
    override suspend fun invoke(pageCursor: Int): DataHolder<ItemsContainer<MovieResponse>> {
        return withContext(Dispatchers.IO) {
            val results = movieRepository.getMovies(pageCursor)
            results.doOnSuccess {
                persistentRepository.insertMovies(it.results)
            }
        }
    }
}