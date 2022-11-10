package com.clarkelamothe.tvshowchallenge.data.services

import com.clarkelamothe.tvshowchallenge.data.models.EpisodeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesService {

    @GET("/shows/{showId}/episodes")
    suspend fun getEpisodesByShowId(
        @Path("showId") showId: Int
    ): Response<List<EpisodeModel>>
}