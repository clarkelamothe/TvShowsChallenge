package com.clarkelamothe.tvshowchallenge.data.services

import com.clarkelamothe.tvshowchallenge.data.models.ShowModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsService {

    @GET("shows")
    suspend fun getShows() : Response<List<ShowModel>>

    @GET("/search/shows")
    suspend fun getShowByQuery(
        @Query("q") query: String
    ): Response<List<ShowModel>>
}