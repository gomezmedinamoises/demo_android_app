package com.example.demoapp.movie_list.presentation.home

/**
 * @author Moises David Gomez Medina
 */

/**
 * Sealed interface that represents different UI events in the movie list context.
 * This allows for exhaustive checking when handling these events.
 */
sealed interface MovieListUiEvent {
    /**
     * Represents a pagination event to fetch more movies from a specified category.
     *
     * @property category The category of movies to paginate through.
     */
    data class Paginate(val category: String) : MovieListUiEvent
    /**
     * Represents a navigation event to transition to a different screen or state.
     */
    data object Navigate : MovieListUiEvent
}