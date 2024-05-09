package com.example.demoapp.movie_list.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Moises David Gomez Medina
 */

/**
 * Database to handle entities such as [MovieEntity]. Also, defines the database version and schema exportation.
 * Extends from [RoomDatabase].
 *
 * @property movieDao Provide access to the database to handle multiple operations.
 */
@Database(
    entities = [MovieEntity::class],
    version = 4,
    exportSchema = false
)
abstract  class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}