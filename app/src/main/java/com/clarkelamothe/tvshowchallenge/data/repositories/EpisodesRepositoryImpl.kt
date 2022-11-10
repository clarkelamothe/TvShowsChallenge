package com.clarkelamothe.tvshowchallenge.data.repositories

import com.clarkelamothe.tvshowchallenge.data.datasources.EpisodesDataSource
import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.models.EpisodeModel
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val episodesDataSource: EpisodesDataSource
): EpisodesRepository {
    override suspend fun getEpisodesByShowId(showId: Int): Resource<List<EpisodeModel>> = episodesDataSource.getEpisodesByShowId(showId)
}