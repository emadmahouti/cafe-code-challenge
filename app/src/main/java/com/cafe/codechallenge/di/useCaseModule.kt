package com.cafe.codechallenge.di

import com.cafe.codechallenge.domain.usecases.GetMovieUseCase
import com.cafe.codechallenge.domain.usecases.GetMovieUseCaseImp
import com.cafe.codechallenge.domain.usecases.GetPersistentMovieUseCase
import com.cafe.codechallenge.domain.usecases.GetPersistentMovieUseCaseImp
import org.koin.dsl.module

/**
 * Created by emadmahouti on 1/29/24
 */
val  useCaseModule = module {
    factory<GetMovieUseCase> { GetMovieUseCaseImp(get(), get()) }
    factory<GetPersistentMovieUseCase> { GetPersistentMovieUseCaseImp(get()) }
}