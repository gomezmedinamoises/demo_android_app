package com.example.demoapp.movie_list.data.local.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Moises David Gomez Medina
 */
@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = false
)
abstract  class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}