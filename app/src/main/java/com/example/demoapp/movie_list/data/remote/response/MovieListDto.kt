package com.example.demoapp.movie_list.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * @author Moises David Gomez Medina
 */
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)