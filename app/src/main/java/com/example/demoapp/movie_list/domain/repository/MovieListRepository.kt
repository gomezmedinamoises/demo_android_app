package com.example.demoapp.movie_list.domain.repository

import com.example.demoapp.movie_list.domain.model.Movie
import com.example.demoapp.movie_list.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Moises David Gomez Medina
 */

/**
 * Repository to handle [List] of [Movie] with their details.
 * Provides functions to fetch data from remote and local.
 */
interface MovieListRepository {
    /**
     * Fetch a [Resource] with a [List] of [Movie] according the [category] and [page] provided.
     * Get data from a remote source can be forced.
     *
     * @param forceFetchFromRemote Indicates if data fetching from a remote source is forced.
     * @param category Category of the movies to fetch.
     * @param page Number of page by the results to obtain.
     *
     * @return A [Flow] of [Resource] with a [List] of [Movie].
     */
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ) : Flow<Resource<List<Movie>>>

    /**
     * Fetch the movie details according the id provided.
     *
     * @param id Allows to identify the movie to fetch.
     *
     * @return A [Flow] of [Resource] with the details of [Movie].
     */
    suspend fun getMovie(id: Int) : Flow<Resource<Movie>>
}