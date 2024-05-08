package com.example.demoapp.movie_list.data.remote.response

/**
 * @author Moises David Gomez Medina
 */
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)