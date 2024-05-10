package com.mmorikawa.feature.reading_list

import androidx.lifecycle.ViewModel
import com.mmorikawa.book_recommender.core.data.repository.ReadingListRepository
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadingListViewModel @Inject constructor(
    val readingListRepository: ReadingListRepository
) : ViewModel() {
    // TODO: Set up repository
    var uiState = UiState.Loading

}