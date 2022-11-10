package com.clarkelamothe.tvshowchallenge.data.services

import com.clarkelamothe.tvshowchallenge.data.models.ShowModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShowsService {

    @GET("shows")
    suspend fun getShows() : Response<List<ShowModel>>

    @GET("shows/{showId}")
    suspend fun getShowById(
        @Path("showId") showId: Int
    ): Response<ShowModel>
}