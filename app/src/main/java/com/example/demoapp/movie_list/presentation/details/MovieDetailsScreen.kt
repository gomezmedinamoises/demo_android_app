package com.example.demoapp.movie_list.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.demoapp.R
import com.example.demoapp.movie_list.data.remote.MovieApi
import com.example.demoapp.movie_list.utils.RatingBar
import com.example.demoapp.ui.MovieFontSize
import com.example.demoapp.ui.MovieIconSize
import com.example.demoapp.ui.MoviePaddingValues
import com.example.demoapp.ui.MovieRoundedCorner
import com.example.demoapp.ui.MovieSpacerValues

/**
 * @author Moises David Gomez Medina
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    navController: NavController
) {

    val movieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()
    val detailsState = movieDetailsViewModel.detailsState.collectAsState().value

    val backDropImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.movie_details)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (backDropImageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(MovieIconSize.medium),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailsState.movie?.title
                        )
                    }
                }

                if (backDropImageState is AsyncImagePainter.State.Success) {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = backDropImageState.painter,
                        contentDescription = detailsState.movie?.title,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(MovieSpacerValues.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MoviePaddingValues.large)
                ) {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                    ) {
                        if (posterImageState is AsyncImagePainter.State.Error) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(MovieRoundedCorner.xSmall))
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(MovieIconSize.medium),
                                    imageVector = Icons.Rounded.ImageNotSupported,
                                    contentDescription = detailsState.movie?.title
                                )
                            }
                        }

                        if (posterImageState is AsyncImagePainter.State.Success) {

                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(MovieRoundedCorner.small)),
                                painter = posterImageState.painter,
                                contentDescription = detailsState.movie?.title,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    detailsState.movie?.let { movie ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(start = MoviePaddingValues.large),
                                text = movie.title,
                                fontSize = MovieFontSize.large,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(MovieSpacerValues.medium))

                            Row(
                                modifier = Modifier
                                    .padding(start = MoviePaddingValues.large)
                            ) {
                                RatingBar(
                                    starsModifier = Modifier.size(MoviePaddingValues.xLarge),
                                    rating = movie.vote_average / 2
                                )

                                Text(
                                    modifier = Modifier.padding(start = MoviePaddingValues.xSmall),
                                    text = movie.vote_average.toString().take(3),
                                    color = Color.LightGray,
                                    fontSize = MovieFontSize.small,
                                    maxLines = 1
                                )
                            }

                            Spacer(modifier = Modifier.height(MovieSpacerValues.small))

                            Text(
                                modifier = Modifier.padding(start = MoviePaddingValues.large),
                                text = stringResource(R.string.language) + movie.original_language,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(MovieSpacerValues.small))

                            Text(
                                modifier = Modifier.padding(start = MoviePaddingValues.large),
                                text = stringResource(R.string.release_data) + movie.release_date,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(MovieSpacerValues.small))

                            Text(
                                modifier = Modifier.padding(start = MoviePaddingValues.large),
                                text = "${movie.vote_count} Votes",
                                color = Color.White
                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(MovieSpacerValues.large))

                Text(
                    modifier = Modifier.padding(start = MoviePaddingValues.large),
                    text = stringResource(R.string.overview),
                    fontSize = MovieFontSize.large,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(MovieSpacerValues.xSmall))

                detailsState.movie?.let {
                    Text(
                        modifier = Modifier.padding(start = MoviePaddingValues.large),
                        text = it.overview,
                        fontSize = MovieFontSize.medium,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(MovieSpacerValues.large))
            }
        }
    )
}