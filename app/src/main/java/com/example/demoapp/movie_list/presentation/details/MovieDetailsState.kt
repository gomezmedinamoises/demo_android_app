package com.example.demoapp.movie_list.presentation.details

import com.example.demoapp.movie_list.domain.model.Movie
import javax.inject.Inject

/**
 * @author Moises David Gomez Medina
 */

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)