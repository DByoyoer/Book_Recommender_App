package com.mmorikawa.feature.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
internal fun RatingDetailRoute(
    viewModel: RatingDetailViewModel = hiltViewModel()
) {
    val ratingUiState = viewModel.uiState
    RatingDetailScreen(ratingUiState, viewModel::updateRatingText)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RatingDetailScreen(
    ratingUiState: UiState<Rating>, onRatingTextUpdate: (String) -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Book Rating") }) }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(BookRecIcons.SaveCheckmark, contentDescription = "Save rating button")
        }
    }) { padding ->
        UiStateWrapper(uiState = ratingUiState) { rating ->
            Column(modifier = Modifier.padding(padding)) {
                Row {
                    TextField(value = rating.ratingText,
                        onValueChange = { onRatingTextUpdate(it) },
                        label = { Text("Review") },
                        placeholder = { Text("Enter review here") })
                }
            }
        }
    }

}