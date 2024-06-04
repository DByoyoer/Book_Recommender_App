package com.mmorikawa.feature.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.asUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {
    val uiState: StateFlow<UiState<List<SimpleBook>>> =
        bookRepository.getRecommendations().asUiState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )


}