package com.mmorikawa.feature.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
internal fun RatingDetailRoute(
    viewModel: RatingDetailViewModel = hiltViewModel()
) {
    val ratingUiState = viewModel.uiState
    RatingDetailScreen(ratingUiState)
}

@Composable
internal fun RatingDetailScreen(
    ratingUiState: UiState<Rating>
) {
    UiStateWrapper(uiState = ratingUiState) {
        Text(it.book.id.toString())
    }
}