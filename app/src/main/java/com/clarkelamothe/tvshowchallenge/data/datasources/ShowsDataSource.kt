package com.clarkelamothe.tvshowchallenge.data.datasources

import com.clarkelamothe.tvshowchallenge.data.services.ShowsService
import javax.inject.Inject

class ShowsDataSource @Inject constructor(
    private val showService: ShowsService
): BaseDataSource() {

    suspend fun getShows() = getResult { showService.getShows() }

    suspend fun getShowsById(id: Int) = getResult { showService.getShowById(id) }
}