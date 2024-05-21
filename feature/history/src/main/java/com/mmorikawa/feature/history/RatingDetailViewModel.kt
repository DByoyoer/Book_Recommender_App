package com.mmorikawa.feature.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.book_recommender.core.data.repository.RatingRepository
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.feature.history.navigation.RatingDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
class RatingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val ratingRepository: RatingRepository,
    private val bookRepository: BookRepository
) : ViewModel() {
    private val ratingDetailArgs: RatingDetailArgs = RatingDetailArgs(savedStateHandle)
    private val bookId = ratingDetailArgs.bookId
    var uiState by mutableStateOf<UiState<Rating>>(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            ratingRepository.getRating(bookId).let { rating ->
                uiState = if (rating != null) {
                    UiState.Success(rating)
                } else {
                    UiState.Success(
                        Rating(
                            book = bookRepository.getSimpleBookById(bookId),
                            score = 0,
                            ratingText = "",
                            dateCreated = Clock.System.now(),
                            dateUpdated = Clock.System.now()
                        )
                    )
                }
            }
        }
    }



}