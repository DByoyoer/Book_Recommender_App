package com.mmorikawa.feature.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
internal fun RatingDetailRoute(
    viewModel: RatingDetailViewModel = hiltViewModel()
) {
    val ratingUiState by viewModel.ratingUiState.collectAsStateWithLifecycle()
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