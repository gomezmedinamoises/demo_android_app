package com.example.demoapp.movie_list.domain.repository

import com.example.demoapp.movie_list.domain.model.Movie
import com.example.demoapp.movie_list.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Moises David Gomez Medina
 */

interface MovieListRepository {

    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ) : Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int) : Flow<Resource<Movie>>
}