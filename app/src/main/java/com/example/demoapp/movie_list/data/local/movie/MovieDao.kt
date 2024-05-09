package com.example.demoapp.movie_list.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * @author Moises David Gomez Medina
 */
@Dao
interface MovieDao {
    /**
     * Insert or update a list of movies.
     *
     * @param movieList [List] of [MovieEntity] that will be inserted or updated.
     */
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    /**
     * Get a movie by id.
     *
     * @param id Movie id used to retrieve a specific movie.
     *
     * @return A [MovieEntity] according the id provided.
     */
    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    /**
     * Get a movie list by category.
     *
     * @param category String used to determine the movie's category to fetch.
     *
     * @return A [List] of [MovieEntity].
     */
    @Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>
}