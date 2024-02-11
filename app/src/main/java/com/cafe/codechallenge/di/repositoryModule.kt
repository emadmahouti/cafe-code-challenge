package com.cafe.codechallenge.di


import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepositoryImp
import com.cafe.codechallenge.data.remote.repositories.MovieRepository
import com.cafe.codechallenge.data.remote.repositories.MovieRepositoryImp
import org.koin.dsl.module

/**
 * Created by emadmahouti on 5/13/23
 */
val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImp(get(), get()) }
    factory<PersistentMovieRepository> { PersistentMovieRepositoryImp(get()) }
}