package com.clarkelamothe.tvshowchallenge.data.datasources

import com.clarkelamothe.tvshowchallenge.data.services.EpisodesService
import javax.inject.Inject

class EpisodesDataSource @Inject constructor(
    private val episodesService: EpisodesService
) : BaseDataSource() {
    suspend fun getEpisodesByShowId(showId: Int) =
        getResult { episodesService.getEpisodesByShowId(showId) }
}