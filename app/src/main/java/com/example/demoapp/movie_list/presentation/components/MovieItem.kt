package com.example.demoapp.movie_list.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.demoapp.movie_list.data.remote.MovieApi
import com.example.demoapp.movie_list.domain.model.Movie
import com.example.demoapp.movie_list.utils.Screen
import com.example.demoapp.movie_list.utils.getAverageColor
import com.example.demoapp.ui.MovieFontSize
import com.example.demoapp.ui.MovieIconSize
import com.example.demoapp.ui.MoviePaddingValues
import com.example.demoapp.ui.MovieRoundedCorner
import com.example.demoapp.ui.MovieSpacerValues

/**
 * @author Moises David Gomez Medina
 */

/**
 * Composable function that displays a movie item within a column layout.
 * It presents movie details including an image, title and a rating bar.
 *
 * @param movie Instance of [Movie] containing movie details.
 * @param navHostController A [NavHostController] to manage navigation events.
 */
@Composable
fun MovieItem(
    movie: Movie,
    navHostController: NavHostController
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + movie.backdropPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(MoviePaddingValues.small)
            .clip(RoundedCornerShape(MovieRoundedCorner.large))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                navHostController.navigate(Screen.Details.route + "/${movie.id}")
            }
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MoviePaddingValues.xSmall)
                    .height(250.dp)
                    .clip(RoundedCornerShape(MovieRoundedCorner.medium))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(MovieIconSize.medium),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = movie.title
                )
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MoviePaddingValues.small)
                    .height(250.dp)
                    .clip(RoundedCornerShape(MovieRoundedCorner.medium)),
                painter = imageState.painter,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(MovieSpacerValues.xSmall))

        Text(
            modifier = Modifier.padding(
                start = MoviePaddingValues.large,
                end = MoviePaddingValues.small
            ),
            text = movie.title,
            color = Color.White,
            fontSize = MovieFontSize.medium,
            maxLines = 1
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MoviePaddingValues.large,
                    bottom = MoviePaddingValues.medium,
                    top = MoviePaddingValues.xSmall
                )
        ) {
            RatingBar(
                starsModifier = Modifier.size(18.dp),
                rating = movie.voteAverage / 2
            )

            Text(
                modifier = Modifier.padding(MoviePaddingValues.xSmall),
                text = movie.voteAverage.toString().take(3),
                color = Color.LightGray,
                fontSize = MovieFontSize.medium,
                maxLines = 1
            )
        }
    }
}