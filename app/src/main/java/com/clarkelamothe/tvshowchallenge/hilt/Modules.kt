package com.clarkelamothe.tvshowchallenge.hilt

import com.clarkelamothe.tvshowchallenge.api.BASE_API_URL
import com.clarkelamothe.tvshowchallenge.data.datasources.EpisodesDataSource
import com.clarkelamothe.tvshowchallenge.data.datasources.ShowsDataSource
import com.clarkelamothe.tvshowchallenge.data.services.EpisodesService
import com.clarkelamothe.tvshowchallenge.data.services.ShowsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Modules {
    @TvShowApp
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideShowsService(@TvShowApp retrofit: Retrofit): ShowsService =
        retrofit.create(ShowsService::class.java)

    @Provides
    fun provideShowsDataSource(showsService: ShowsService) =
        ShowsDataSource(showsService)

    @Provides
    fun provideEpisodesService(@TvShowApp retrofit: Retrofit): EpisodesService =
        retrofit.create(EpisodesService::class.java)

    @Provides
    fun provideEpisodesDataSource(episodesService: EpisodesService) =
        EpisodesDataSource(episodesService)
}