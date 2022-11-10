package com.clarkelamothe.tvshowchallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.models.ShowModel
import com.clarkelamothe.tvshowchallenge.data.repositories.ShowsRepository
import com.clarkelamothe.tvshowchallenge.data.repositories.ShowsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showsRepository: ShowsRepositoryImpl
) : ViewModel() {
    private val _showsState = MutableLiveData<ShowsState>()
    val showsState: LiveData<ShowsState> = _showsState

    fun getShows() {
        _showsState.value = ShowsState.Loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                showsRepository.getShows()
            }.run {
                when (status) {
                    Resource.Status.SUCCESS -> {
                        _showsState.value = ShowsState.Loading(false)
                        _showsState.value = data?.let {
                            ShowsState.Success(it)
                        }
                    }
                    else -> {
                        _showsState.value = ShowsState.Loading(false)
                        _showsState.value = ShowsState.Error(0)
                    }
                }
            }
        }
    }
}

sealed class ShowsState {
    data class Success(val characters: List<ShowModel>) : ShowsState()
    data class Loading(val isLoading: Boolean) : ShowsState()
    data class Error(val resId: Int) : ShowsState()
}