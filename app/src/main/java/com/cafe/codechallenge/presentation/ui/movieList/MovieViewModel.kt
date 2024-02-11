package com.cafe.codechallenge.presentation.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cafe.codechallenge.data.local.repositories.PersistentMovieRepository
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.data.remote.repositories.MovieRepository
import com.cafe.codechallenge.domain.usecases.GetMovieUseCase
import com.cafe.codechallenge.domain.usecases.GetOfflineMovieUseCase
import com.cafe.codechallenge.presentation.common.base.BaseViewModel
import com.cafe.codechallenge.presentation.common.util.Paginator
import com.cafe.codechallenge.presentation.ui.movieList.items.MoviePaginator
import com.cafe.codechallenge.presentation.ui.movieList.items.MoviePaginatorInterface
import com.cafe.codechallenge.util.StateLiveData
import com.cafe.codechallenge.util.catchStateIn
import com.cafe.codechallenge.util.livedata.StoreListLiveData
import com.cafe.codechallenge.util.reset
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val offlineMovieUseCase: GetOfflineMovieUseCase,
    private val dispatcher: CoroutineDispatcher): BaseViewModel() {

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
            postValues({offlineMovieUseCase(page)}, _movieLiveData, paging).catchStateIn(_stateLiveData)
        }
    }
}