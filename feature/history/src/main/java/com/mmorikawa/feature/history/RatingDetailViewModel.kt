package com.mmorikawa.feature.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.RatingRepository
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.feature.history.navigation.RatingDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RatingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val ratingRepository: RatingRepository
) : ViewModel() {
    private val ratingDetailArgs: RatingDetailArgs = RatingDetailArgs(savedStateHandle)
    private val bookId = ratingDetailArgs.bookId

    val ratingUiState: StateFlow<UiState<Rating>> =
        ratingRepository.getRatingStream(bookId).map { UiState.Success(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )
}