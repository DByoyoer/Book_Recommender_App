package com.mmorikawa.feature.history

import androidx.lifecycle.ViewModel
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.BookFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HistoryViewModel : ViewModel() {
    // TODO: Set up repository
    val feedUiState = BookFeedUiState.Success(getFakeUserBookData())
}