package com.example.demoapp.movie_list.presentation.home

/**
 * @author Moises David Gomez Medina
 */

sealed interface MovieListUiEvent {

    data class Paginate(val category: String) : MovieListUiEvent
    data object Navigate : MovieListUiEvent
}