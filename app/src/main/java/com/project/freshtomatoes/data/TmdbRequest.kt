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

class TmdbRequest {
    private val BASE_URL = "api.themoviedb.org"
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    private suspend fun getTmdb(path: List<String>, builder: HttpRequestBuilder.() -> Unit = { }): HttpResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                appendPathSegments("3")
                appendPathSegments(path)
            }
            println(url.buildString())
            headers.append("accept", "application/json")
            headers.append("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOTdjYWVhY2JiM2QxOTkzYTk3YjIwY2RhMTA0OGI0NSIsInN1YiI6IjY1MjZmNzBkY2VkYWM0MDEyMjE4MzEyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LYrbct16U5oMX3ALVsXuNBBCnr9ryRlCMil4V1Xp36k")
            builder()

            // Maybe remove this?
            url {
                parameters.append("language", "en-US")
            }
        }
    }

    private fun cacheMovie(movie: Movie) {
        if(!FreshTomatoes.moviesById.containsKey(movie.id)) {
            FreshTomatoes.moviesById[movie.id] = movie
        }
    }

    private fun cacheMovieResponse(movieResponse: MovieResponse) {
        for(movie in movieResponse.results) {
            cacheMovie(movie)
        }
    }

    suspend fun searchMovies(query: String): MovieResponse {
        val response = getTmdb(listOf("search", "movie")) {
            url {
                parameters.append("query", query)
            }
        }
        return response.body()
    }

    suspend fun popularMovies(page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("movie", "popular")) {
            url {
                parameters.append("page", page.toString())
            }
        }
        return response.body()
    }

    suspend fun nowPlayingMovies(page: Int = 1): MovieResponse {
        val response = getTmdb(listOf("movie", "now_playing")) {
            url {
                parameters.append("page", page.toString())
            }
        }
        return response.body()
    }

    suspend fun details(id: Int): Movie {
        if(FreshTomatoes.moviesById.containsKey(id)) {
            return FreshTomatoes.moviesById[id]!!
        }

        val response = getTmdb(listOf("movie", id.toString()))
        val body: Movie = response.body()

        cacheMovie(body)
        return body
    }
}
