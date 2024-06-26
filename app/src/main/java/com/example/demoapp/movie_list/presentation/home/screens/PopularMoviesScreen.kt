package com.example.demoapp.movie_list.presentation.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.demoapp.movie_list.presentation.home.MovieListState
import com.example.demoapp.movie_list.presentation.home.MovieListUiEvent
import com.example.demoapp.movie_list.presentation.components.MovieItem
import com.example.demoapp.movie_list.utils.Category
import com.example.demoapp.ui.MoviePaddingValues

/**
 * @author Moises David Gomez Medina
 */
@Composable
fun PopularMoviesScreen(
    movieListState: MovieListState,
    navHostController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {
    if (movieListState.popularMovieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                vertical = MoviePaddingValues.small,
                horizontal = MoviePaddingValues.xSmall
            )
        ) {
            items(movieListState.popularMovieList.size) {index ->
                MovieItem(
                    movie = movieListState.popularMovieList[index],
                    navHostController = navHostController
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (index >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
                    onEvent(MovieListUiEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }
}