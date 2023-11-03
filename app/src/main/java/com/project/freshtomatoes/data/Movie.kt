package com.project.freshtomatoes.data

data class Genres(
    val id: Int,
    val name: String
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val genres: List<Genres>,
    val runtime: Int,
    val release_date: String,
    val revenue: Double,
    val tagline: String
)

data class MovieResponse(
    val results: List<Movie>
)
