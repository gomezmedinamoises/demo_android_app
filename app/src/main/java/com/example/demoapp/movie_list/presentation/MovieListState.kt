package com.example.demoapp.movie_list.presentation

import com.example.demoapp.movie_list.domain.model.Movie

/**
 * @author Moises David Gomez Medina
 */

data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList()
)