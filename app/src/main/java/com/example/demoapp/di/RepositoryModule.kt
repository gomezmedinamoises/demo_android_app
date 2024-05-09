package com.example.demoapp.di

import com.example.demoapp.movie_list.data.repository.MovieListRepositoryImpl
import com.example.demoapp.movie_list.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Moises David Gomez Medina
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ) : MovieListRepository
}