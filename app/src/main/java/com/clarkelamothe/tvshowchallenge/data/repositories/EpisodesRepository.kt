package com.clarkelamothe.tvshowchallenge.data.repositories

import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.models.EpisodeModel

interface EpisodesRepository {
    suspend fun getEpisodesByShowId(showId: Int): Resource<List<EpisodeModel>>
}