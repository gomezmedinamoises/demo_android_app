package com.example.demoapp.movie_list.utils

/**
 * @author Moises David Gomez Medina
 */

sealed class Screen(val route: String) {
    data object Home : Screen("main")
    data object PopularMovieList : Screen("popularMovie")
    data object UpcomingMovieList : Screen("upcomingMovie")
    data object Details : Screen("details")
}