package com.cafe.codechallenge.presentation.ui.movieList

import android.net.ConnectivityManager
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
    private val connectivity: ConnectivityManager): BaseViewModel() {

    private val _movieLiveData = MutableLiveData<List<MovieResponse>>()
    private val _stateLiveData = StateLiveData()

    val movieLiveData: LiveData<List<MovieResponse>> = _movieLiveData
    val stateLiveData: LiveData<PageState> = _stateLiveData

    val paging = MoviePaginator(1, MoviePaginatorInterface(this::getMovies))

    init {
        paging.load()
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(dispatcher) {
            val request = elsif(connectivity.isNetworkAvailable(), suspend {movieUseCase(page)}, suspend {persistentMovieUseCase(page)})
            postValues(request, _movieLiveData, paging).catchStateIn(_stateLiveData)
        }
    }

    fun reset() {
        _movieLiveData.reset()
        paging.reset()
    }
}