package com.mmorikawa.feature.reading_list

import androidx.lifecycle.ViewModel
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.BookFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadingListViewModel @Inject constructor() : ViewModel() {
    // TODO: Set up repository
    val feedUiState = BookFeedUiState.Success(getFakeUserBookData())
}