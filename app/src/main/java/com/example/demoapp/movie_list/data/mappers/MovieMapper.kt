package com.example.demoapp.movie_list.data.mappers

import com.example.demoapp.movie_list.data.local.movie.MovieEntity
import com.example.demoapp.movie_list.data.remote.response.MovieDto
import com.example.demoapp.movie_list.domain.model.Movie

/**
 * @author Moises David Gomez Medina
 */

/**
 * Converts a [MovieDto] into a [MovieEntity].
 *
 * @param category Movie's category that will be assigned to [MovieEntity]
 * @return Returns a [MovieEntity]
 */
fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        voteAverage = voteAverage ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = voteCount ?: 0,
        id = id ?: -1,
        originalTitle = originalTitle ?: "",
        video = video ?: false,

        category = category,

        genreIds = try {
            genreIds?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        }
    )
}

/**
 * Converts a [MovieEntity] into a [Movie].
 *
 * @param category Movie's category that will be assigned to [Movie]
 * @return Returns a [Movie]
 */
fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        id = id,
        adult = adult,
        originalTitle = originalTitle,

        category = category,

        genreIds = try {
            genreIds.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        }
    )
}