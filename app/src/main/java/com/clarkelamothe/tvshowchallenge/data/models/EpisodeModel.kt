package com.clarkelamothe.tvshowchallenge.data.models

data class EpisodeModel(
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val runtime: Int,
    val season: Int,
    val summary: String,
    val type: String,
    val url: String
)