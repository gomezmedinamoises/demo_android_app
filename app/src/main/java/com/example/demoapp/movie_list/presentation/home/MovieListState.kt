package com.example.demoapp.movie_list.presentation.home

import com.example.demoapp.movie_list.domain.model.Movie

/**
 * @author Moises David Gomez Medina
 */

/**
 * Represents the state of the [List] of [Movie] UI.
 *
 * @property isLoading Indicates if the [Movie] is currently being loaded.
 * @property popularMovieListPage Current page number for the popular movies pagination.
 * @property upcomingMovieListPage Current page number for the upcoming movies pagination.
 * @property isCurrentPopularScreen Indicates if the current screen is displaying popular movies.
 * @property popularMovieList A list of [Movie] objects representing the popular movies.
 * @property upcomingMovieList A list of [Movie] objects representing the upcoming movies.
 */
data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList()
)