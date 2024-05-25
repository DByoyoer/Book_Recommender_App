package com.mmorikawa.feature.history

import android.util.Log
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
    var isNewRating by mutableStateOf(true)
        private set
    var uiState by mutableStateOf<UiState<Rating>>(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            ratingRepository.getRating(bookId).let { rating ->
                uiState = if (rating != null) {
                    isNewRating = false
                    UiState.Success(rating)
                } else {
                    UiState.Success(
                        Rating(
                            book = bookRepository.getSimpleBookById(bookId),
                            score = 0f,
                            ratingText = "",
                            dateCreated = Clock.System.now(),
                            dateUpdated = Clock.System.now()
                        )
                    )
                }
            }
        }
    }

    fun updateRatingText(ratingText: String) {
        val rating = (uiState as UiState.Success).data

        uiState = UiState.Success(
            Rating(
                book = rating.book,
                score = rating.score,
                ratingText = ratingText,
                dateUpdated = rating.dateUpdated,
                dateCreated = rating.dateCreated
            )
        )
    }

    fun updateScore(score: Float) {
        val rating = (uiState as UiState.Success).data

        uiState = UiState.Success(
            Rating(
                book = rating.book,
                score = score,
                ratingText = rating.ratingText,
                dateUpdated = rating.dateUpdated,
                dateCreated = rating.dateCreated
            )
        )
    }

    fun saveRating() {
        var rating = (uiState as UiState.Success).data
        rating = Rating(
            book = rating.book,
            score = rating.score,
            ratingText = rating.ratingText,
            dateUpdated = Clock.System.now(),
            dateCreated = rating.dateCreated
        )
        Log.d("Rating Detail", "New($isNewRating)Saved rating: $rating")
        viewModelScope.launch {
            if (isNewRating) {
                ratingRepository.createRating(rating)
                isNewRating = false
            } else {
                ratingRepository.updateRating(rating)
            }
        }

    }


}