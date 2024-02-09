package com.cafe.codechallenge.presentation.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cafe.codechallenge.data.remote.model.ItemsContainer
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.data.remote.repositories.MovieRepository
import com.cafe.codechallenge.presentation.common.base.BaseViewModel
import com.cafe.codechallenge.util.StateLiveData
import com.cafe.codechallenge.util.catchStateIn
import com.cafe.codechallenge.util.livedata.StoreListLiveData
import com.pixy.codebase.common.viewgroup.items.PageState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieViewModel(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher): BaseViewModel() {

    private val defaultPage = 1

     val _movieLiveData = StoreListLiveData<MovieResponse>()
    val movieLiveData: LiveData<ItemsContainer<MovieResponse>> = _movieLiveData

    private val _stateLiveData = StateLiveData()
    val stateLiveData: LiveData<PageState> = _stateLiveData

    init {
        getMovies(defaultPage)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(dispatcher) {
            postValue({repository.getMovies(page)}, _movieLiveData).catchStateIn(_stateLiveData)
        }
    }
}