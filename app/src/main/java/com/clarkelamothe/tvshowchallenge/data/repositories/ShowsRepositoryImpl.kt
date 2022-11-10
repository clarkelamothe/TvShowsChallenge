package com.clarkelamothe.tvshowchallenge.data.repositories

import com.clarkelamothe.tvshowchallenge.data.datasources.Resource
import com.clarkelamothe.tvshowchallenge.data.datasources.ShowsDataSource
import com.clarkelamothe.tvshowchallenge.data.models.ShowModel
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val showsDataSource: ShowsDataSource
): ShowsRepository {
    override suspend fun getShows(): Resource<List<ShowModel>> = showsDataSource.getShows()

    override suspend fun getShowByQuery(showName: String): Resource<List<ShowModel>> = showsDataSource.getShowsByName(showName)
}