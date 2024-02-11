package com.cafe.codechallenge.presentation.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.domain.usecases.GetMovieUseCase
import com.cafe.codechallenge.domain.usecases.GetPersistentMovieUseCase
import com.cafe.codechallenge.presentation.common.base.BaseViewModel
import com.cafe.codechallenge.presentation.ui.movieList.items.MoviePaginator
import com.cafe.codechallenge.presentation.ui.movieList.items.MoviePaginatorInterface
import com.cafe.codechallenge.util.*
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val persistentMovieUseCase: GetPersistentMovieUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val isNetworkAvailable: Boolean): BaseViewModel() {

    private val mutableMovieLiveData = MutableLiveData<List<MovieResponse>>()
    private val mutableStateLiveData = StateLiveData()

    val movieLiveData: LiveData<List<MovieResponse>> = mutableMovieLiveData
    val stateLiveData: LiveData<PageState> = mutableStateLiveData

    val paging = MoviePaginator(1, MoviePaginatorInterface(this::getData))

    init {
        paging.load()
    }

    private fun getData(page: Int) {
        if(isNetworkAvailable) getMovieList(page) else getLocalMovieList(page)
    }

    fun getLocalMovieList(page: Int) {
        viewModelScope.launch(dispatcher) {
            postValues({persistentMovieUseCase(page)}, mutableMovieLiveData, paging).catchStateIn(mutableStateLiveData)
        }
    }

    fun getMovieList(page: Int) {
        viewModelScope.launch(dispatcher) {
            postValues({movieUseCase(page)}, mutableMovieLiveData, paging).catchStateIn(mutableStateLiveData)
        }
    }



    fun reset() {
        mutableMovieLiveData.invalidate()
        paging.invalidate()
    }
}