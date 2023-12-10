package com.project.freshtomatoes.data

import com.project.freshtomatoes.ui.FreshTomatoes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.serialization.gson.gson

// Coded by all (everyone editied)
/**
 * TmdbRequest class to request data from https://www.themoviedb.org/.
 * Uses ktor http client
 */
class TmdbRequest {
    // Tmdb base request URL.
    private val BASE_URL = "api.themoviedb.org"

    // Initialize Ktor HttpClient instance.
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    /**
     * Creates a GET request to Tmdb, using the passed path and builder (HttpRequestBuilder).
     *
     * @param path Path to append to URL.
     * @param builder Http request builder.
     * @return The Ktor HttpResponse.
     */
    private suspend fun getTmdb(path: List<String>, builder: HttpRequestBuilder.() -> Unit = { }): HttpResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                // Append API version.
                appendPathSegments("3")
                appendPathSegments(path)
            }

            headers.append("accept", "application/json")
            headers.append("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOTdjYWVhY2JiM2QxOTkzYTk3YjIwY2RhMTA0OGI0NSIsInN1YiI6IjY1MjZmNzBkY2VkYWM0MDEyMjE4MzEyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LYrbct16U5oMX3ALVsXuNBBCnr9ryRlCMil4V1Xp36k")
            builder()

            // Force en-US.
            url {
                parameters.append("language", "en-US")
            }
        }
    }

    /**
     * Cache a movie in a hashmap using movieId as the key.
     */
    private fun cacheMovie(movie: Movie) {
        if (!FreshTomatoes.moviesById.containsKey(movie.id)) {
            FreshTomatoes.moviesById[movie.id] = movie
        }
    }

    /**
     * Searches for movies using the string query.
     *
     * @param query Movie name query.
     * @return MovieResponse, which contains a list of movies.
     */
    suspend fun searchMovies(query: String): MovieResponse {
        val response = getTmdb(listOf("search", "movie")) {
            url {
                parameters.append("query", query)
            }
        }
        return response.body()
    }

    /**
     * Gets popular movies. Caller may also specify page number.
     *
     * @param page Page number of movies.
     * @return MovieResponse, which contains a list of movies.
     */
    suspend fun popularMovies(page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("movie", "popular")) {
            url {
                parameters.append("page", page.toString())
            }
        }
        return response.body()
    }

    /**
     * Gets now playing movies. Caller may also specify page number.
     *
     * @param page Page number of movies.
     * @return MovieResponse, which contains a list of movies.
     */
    suspend fun nowPlayingMovies(page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("movie", "now_playing")) {
            url {
                parameters.append("page", page.toString())
            }
        }
        return response.body()
    }

    /**
     * Gets top rated movies. Caller may also specify page number.
     *
     * @param page Page number of movies.
     * @return MovieResponse, which contains a list of movies.
     */
    suspend fun topRatedMovies(page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("movie", "top_rated")) {
            url {
                parameters.append("page", page.toString())
            }
        }
        return response.body()
    }

    /**
     * Gets movie details for a given movie id.
     *
     * @param id The movie ID to query details for.
     * @return Movie, containing all movie details.
     */
    suspend fun details(id: Int): Movie {
        // If the movie details have already been queried, used the cached Movie.
        if (FreshTomatoes.moviesById.containsKey(id)) {
            return FreshTomatoes.moviesById[id]!!
        }

        // If not, query and cache the details.
        val response = getTmdb(listOf("movie", id.toString()))
        val body: Movie = response.body()
        cacheMovie(body)

        // Return the movie.
        return body
    }

    /**
     * Gets a list of movies by genre id.
     *
     * @param genreId Genre ID for query movies for.
     * @param page Page number to query movies from.
     */
    suspend fun getMoviesByGenre(genreId: Int, page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("discover", "movie")) {
            url {
                parameters.apply {
                    append("page", page.toString())
                    append("with_genres", genreId.toString())
                }
            }
        }
        return response.body()
    }

    /**
     * Gets movies with action genre. Caller may also specify page number.
     *
     * @param page Page to get movies from.
     */
    suspend fun getActionMovies(page: Int = 1): MovieResponse {
        return getMoviesByGenre(28, page)
    }

    /**
     * Gets movies with adventure genre. Caller may also specify page number.
     *
     * @param page Page to get movies from.
     */
    suspend fun getAdventureMovies(page: Int = 1): MovieResponse {
        return getMoviesByGenre(12, page)
    }
}
