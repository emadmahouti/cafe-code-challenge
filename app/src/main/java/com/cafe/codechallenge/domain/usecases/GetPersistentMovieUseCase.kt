package com.cafe.codechallenge.domain.usecases

import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.model.DataHolder
import com.cafe.codechallenge.data.model.ItemsContainer
import com.cafe.codechallenge.data.model.MappableItemContainer
import com.cafe.codechallenge.domain.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by emadmahouti on 2/11/24
 */
interface GetPersistentMovieUseCase: UseCase {
    suspend operator fun invoke(pageCursor: Int, perPage: Int = 10): DataHolder<ItemsContainer<MovieEntity>>
}

class GetPersistentMovieUseCaseImp(
    private val persistentRepository: PersistentMovieRepository,
) : GetPersistentMovieUseCase {
    override suspend fun invoke(pageCursor: Int, perPage: Int): DataHolder<ItemsContainer<MovieEntity>> {
         return withContext(Dispatchers.IO) {
            val result = persistentRepository.getMovies(perPage, perPage * (pageCursor - 1))
            val count = persistentRepository.count()

            val itemContainer = MappableItemContainer(result, pageCursor, count / perPage, count)
            DataHolder.Success(itemContainer.map())
        }
    }
}