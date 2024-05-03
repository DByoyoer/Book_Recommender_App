package com.mmorikawa.feature.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.core.model.UserBookInfo
import com.mmorikawa.core.model.UserData
import com.mmorikawa.core.ui.BookFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {
    // TODO: Set up repository
    var uiState: BookFeedUiState = BookFeedUiState.Loading

    init {
        viewModelScope.launch {
            uiState = BookFeedUiState.Success(
                bookRepository.getRecommendations()
                    .map { UserBookInfo(it, UserData(setOf<Int>(), setOf<Int>())) })
        }
    }
}