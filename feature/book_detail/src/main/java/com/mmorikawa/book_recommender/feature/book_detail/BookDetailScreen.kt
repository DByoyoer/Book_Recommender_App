package com.mmorikawa.book_recommender.feature.book_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper

@Composable
internal fun BookDetailRoute(
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val bookDetailUiState by viewModel.bookDetailUiState.collectAsStateWithLifecycle()
    BookDetailScreen(bookDetailUiState)
}

@Composable
internal fun BookDetailScreen(
    bookDetailUiState: UiState<DetailedBook>
) {
    UiStateWrapper(uiState = bookDetailUiState) {
        Text(text = it.toString())
    }
}