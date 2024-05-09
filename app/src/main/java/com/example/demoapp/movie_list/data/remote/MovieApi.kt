package com.example.demoapp.movie_list.data.remote

import com.example.demoapp.movie_list.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Moises David Gomez Medina
 */

/**
 * Defines the endpoints to fetch data from API.
 * Provides methods to fetch a list of movies by category.
 */
interface MovieApi {

    /**
     * Fetch a list of movies by category.
     *
     * @param category Category of the movie to fetch. Example: popular or upcoming.
     * @param page Number of page's results to fetch.
     * @param apiKey Key used to validate the HTTP request.
     *
     * @return A [MovieListDto] according the [category] and [page] provided.
     */
    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieListDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "494653bb96f95943cb8b3b1a8847ddfb"
    }
}