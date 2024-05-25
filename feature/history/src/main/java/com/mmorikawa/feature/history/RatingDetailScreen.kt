package com.mmorikawa.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
internal fun RatingDetailRoute(
    viewModel: RatingDetailViewModel = hiltViewModel()
) {
    val ratingUiState = viewModel.uiState
    RatingDetailScreen(
        ratingUiState,
        viewModel::updateRatingText,
        viewModel::updateScore,
        viewModel::saveRating
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RatingDetailScreen(
    ratingUiState: UiState<Rating>,
    onRatingTextUpdate: (String) -> Unit,
    onRatingScoreUpdate: (Float) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    var sliderPosition by remember {
        mutableFloatStateOf(0f)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Book Rating") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(BookRecIcons.Trash, contentDescription = "Delete rating button.")
                    }
                })
        },
    ) { padding ->
        UiStateWrapper(uiState = ratingUiState) { rating ->
            Column(
                modifier = Modifier.padding(padding), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                BookHeader(book = rating.book)

                RatingScoreSlider(
                    sliderPosition = rating.score,
                    onScoreChange = onRatingScoreUpdate
                )

                ReviewTextField(text = rating.ratingText, onValueChange = onRatingTextUpdate)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                        onClick = { /*TODO*/ }) {
                        Text("Cancel")
                    }
                    Button(modifier = Modifier
                        .weight(1f)
                        .padding(8.dp), onClick = { onSaveButtonClick() }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewTextField(text: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.75f)
            .fillMaxWidth()
            .padding(16.dp),
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text("Review") },
        placeholder = { Text("Enter review here") },
    )
}

@Composable
fun BookHeader(book: SimpleBook) {
    Row {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = "${book.title} cover image.",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .padding(16.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                book.title, fontSize = 24.sp, modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                "Author(s): ${
                    book.authors.toString().removeSurrounding(prefix = "[", suffix = "]")
                }"
            )
            Text(
                "Genre(s): ${
                    book.genres.toString().removeSurrounding(prefix = "[", suffix = "]")
                }"
            )
        }
    }
}

@Composable
fun RatingScoreSlider(sliderPosition: Float, onScoreChange: (Float) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Slider(
            value = sliderPosition,
            onValueChange = onScoreChange,
            steps = 49,
            valueRange = 0f..5f
        )
        Text("Rating: %.1f".format(sliderPosition))

    }
}

