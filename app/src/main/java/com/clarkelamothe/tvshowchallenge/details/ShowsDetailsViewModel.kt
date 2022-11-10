package com.clarkelamothe.tvshowchallenge.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.models.EpisodeModel
import com.clarkelamothe.tvshowchallenge.data.repositories.EpisodesRepository
import com.clarkelamothe.tvshowchallenge.data.repositories.EpisodesRepositoryImpl
import com.clarkelamothe.tvshowchallenge.home.ShowsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowsDetailsViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepositoryImpl
) : ViewModel() {
    private val _episodesState = MutableLiveData<EpisodesState>()
    val episodesState: LiveData<EpisodesState> = _episodesState

    fun getEpisodesByShowId(showId: Int) {
        _episodesState.value = EpisodesState.Loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                episodesRepository.getEpisodesByShowId(showId)
            }.run {
                when (status) {
                    Resource.Status.SUCCESS -> {
                        _episodesState.value = EpisodesState.Loading(false)
                        _episodesState.value = data?.let {
                            EpisodesState.Success(it)
                        }
                    }
                    else -> {
                        _episodesState.value = EpisodesState.Loading(false)
                        _episodesState.value = EpisodesState.Error(0)
                    }
                }
            }
        }
    }
}

sealed class EpisodesState {
    data class Success(val episodes: List<EpisodeModel>) : EpisodesState()
    data class Loading(val isLoading: Boolean) : EpisodesState()
    data class Error(val resId: Int) : EpisodesState()
}