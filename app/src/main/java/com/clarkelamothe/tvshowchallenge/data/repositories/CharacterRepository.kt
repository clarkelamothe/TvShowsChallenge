package com.clarkelamothe.tvshowchallenge.data.repositories

import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.models.ShowModel

interface ShowsRepository {
    suspend fun getShows(): Resource<List<ShowModel>>

    suspend fun getShowById(showId: Int): Resource<ShowModel>
}