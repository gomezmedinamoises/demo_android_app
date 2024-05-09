package com.example.demoapp.movie_list.utils

/**
 * @author Moises David Gomez Medina
 */

/**
 * Sealed class representing navigation destinations within an application with a defined set of screens.
 *
 * Each object within this sealed class represents a specific screen, carrying its own unique route identifier.
 *
 * @param route The route string identifier for each screen.
 */
sealed class Screen(val route: String) {
    data object Home : Screen("main")
    data object PopularMovieList : Screen("popularMovie")
    data object UpcomingMovieList : Screen("upcomingMovie")
    data object Details : Screen("details")
}