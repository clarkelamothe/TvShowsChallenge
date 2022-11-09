package com.clarkelamothe.tvshowchallenge.data.models

data class ShowModel(
    val averageRuntime: Int,
    val genres: List<String>,
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val runtime: Int,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
)