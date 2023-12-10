package com.project.freshtomatoes.data

// Coded by Jose

/**
 * Represents a movie genre. Templated to match Tmdb response JSON.
 */
data class Genre(
    val id: Int,
    val name: String
)

/**
 * Represents a movie. Templated to match Tmdb response JSON.
 */
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val genres: List<Genre>,
    val runtime: Int,
    val release_date: String,
    val revenue: Double,
    val tagline: String
)

/**
 * Represents a response from Tmdb.
 */
data class MovieResponse(
    val results: List<Movie>
)
