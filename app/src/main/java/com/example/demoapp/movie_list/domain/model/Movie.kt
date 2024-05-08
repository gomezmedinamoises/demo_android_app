package com.example.demoapp.movie_list.domain.model

/**
 * @author Moises David Gomez Medina
 */

data class Movie(
    val adult: Boolean?,
    val backdrop_path: String?,
    val category: String,
    val genre_ids: List<Int>,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)