package com.example.demoapp.movie_list.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.movie_list.domain.model.Movie
import com.example.demoapp.movie_list.domain.repository.MovieListRepository
import com.example.demoapp.movie_list.utils.Category
import com.example.demoapp.movie_list.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Moises David Gomez Medina
 */

/**
 * [ViewModel] for managing the UI-related data concerning movie lists.
 * This ViewModel is injected with a repository to fetch movie data.
 *
 * @property movieListRepository The repository responsible to handle movie data operations.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovieList(false)
        getUpcomingMovieList(false)
    }

    /**
     * Handles various UI events related to movie lists.
     *
     * @param event The event to handle.
     */
    fun onEvent(event: MovieListUiEvent) {
        when(event) {
            MovieListUiEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
                    )
                }
            }
            is MovieListUiEvent.Paginate -> {
                if (event.category == Category.POPULAR) {
                    getPopularMovieList(true)
                } else if (event.category == Category.UPCOMING) {
                    getUpcomingMovieList(true)
                }
            }
        }
    }

    private fun getPopularMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                handleResult(result, isPopular = true)
            }
        }
    }

    private fun getUpcomingMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.UPCOMING,
                movieListState.value.upcomingMovieListPage
            ).collectLatest { result ->
                handleResult(result, isPopular = false)
            }
        }
    }

    /**
     * Handles the result of the movie list fetch operation.
     *
     * @param result The result of the fetch operation.
     * @param isPopular Indicates if the list is popular movies (true) or upcoming movies (false).
     */
    private fun handleResult(result: Resource<List<Movie>>, isPopular: Boolean) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { list ->
                    _movieListState.update {
                        it.copy(
                            popularMovieList = if (isPopular) it.popularMovieList + list.shuffled() else it.popularMovieList,
                            upcomingMovieList = if (!isPopular) it.upcomingMovieList + list.shuffled() else it.upcomingMovieList,
                            popularMovieListPage = if (isPopular) it.popularMovieListPage + 1 else it.popularMovieListPage,
                            upcomingMovieListPage = if (!isPopular) it.upcomingMovieListPage + 1 else it.upcomingMovieListPage
                        )
                    }
                }
            }
            is Resource.Loading -> {
                _movieListState.update { it.copy(isLoading = result.isLoading) }
            }
            is Resource.Error -> {
                _movieListState.update { it.copy(isLoading = false) }
            }
        }
    }
}