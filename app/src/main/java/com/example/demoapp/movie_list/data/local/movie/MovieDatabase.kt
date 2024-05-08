package com.example.demoapp.movie_list.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Moises David Gomez Medina
 */
@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract  class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}