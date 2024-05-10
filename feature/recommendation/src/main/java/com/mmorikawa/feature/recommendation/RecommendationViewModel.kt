package com.mmorikawa.feature.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {
    // TODO: Set up repository
    val uiState: StateFlow<UiState<List<SimpleBook>>> =
        bookRepository.getSimpleBooksStream(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            .map { UiState.Success(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    init {

    }


}