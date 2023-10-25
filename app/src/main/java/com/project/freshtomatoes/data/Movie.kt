package com.project.freshtomatoes.data

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String
)

data class MovieResponse(
    val results: List<Movie>
)
