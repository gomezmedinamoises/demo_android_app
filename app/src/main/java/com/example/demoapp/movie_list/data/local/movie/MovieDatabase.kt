package com.example.demoapp.movie_list.data.local.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
    version = 3,
    exportSchema = false
)
abstract  class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}