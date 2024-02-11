package com.cafe.codechallenge.domain.usecases

import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.remote.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by emadmahouti on 2/11/24
 */
interface GetOfflineMovieUseCase {
    suspend operator fun invoke(pageCursor: Int, perPage: Int = 10): DataHolder<ItemsContainer<MovieResponse>>
}

class GetOfflineMovieUseCaseImp(
    private val persistentRepository: PersistentMovieRepository,
) : GetOfflineMovieUseCase {
    override suspend fun invoke(pageCursor: Int, perPage: Int): DataHolder<ItemsContainer<MovieResponse>> {
        return withContext(Dispatchers.IO) {
            val result = persistentRepository.getMovies(perPage, perPage * (pageCursor - 1))
            val count = persistentRepository.count()

            val itemContainer = ItemsContainer(result, pageCursor, count / perPage, count)
            DataHolder.Success(itemContainer)
        }
    }
}