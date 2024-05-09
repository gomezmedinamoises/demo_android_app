package com.example.demoapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.demoapp.movie_list.presentation.details.MovieDetailsScreen
import com.example.demoapp.movie_list.presentation.home.screens.HomeScreen
import com.example.demoapp.movie_list.utils.Screen

/**
 * @author Moises David Gomez Medina
 */

/**
 * Sets up the navigation graph for the app using Jetpack Compose Navigation.
 * This function defines all the navigation routes and associated screens.
 *
 * @Composable indicates that this function is a composable and can be used to compose UI.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            Screen.Details.route + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {type = NavType.IntType}
            )
        ) {
            MovieDetailsScreen(navController = navController)
        }
    }
}