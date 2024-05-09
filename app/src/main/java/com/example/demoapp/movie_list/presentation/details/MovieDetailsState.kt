package com.example.demoapp.movie_list.presentation.details

import com.example.demoapp.movie_list.domain.model.Movie
import javax.inject.Inject

/**
 * @author Moises David Gomez Medina
 */

/**
 * Represents the state of a UI component concerned with displaying movie details.
 *
 * @property isLoading Indicates if the movie details are currently being loaded.
 * @property movie The movie details currently displayed. This can be null if no movie is selected or if movie details have failed to load.
 */
data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)