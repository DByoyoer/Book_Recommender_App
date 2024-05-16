package com.mmorikawa.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.RatingRepository
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val ratingRepository: RatingRepository
) : ViewModel() {
    val chipState = MutableStateFlow<ChipOptions>(ChipOptions.ALL_TIME)
    val ratingFeedUiState: StateFlow<UiState<List<Rating>>> = chipState.flatMapLatest {
        if (it == ChipOptions.ALL_TIME) {
            ratingRepository.getRatingStream(Instant.DISTANT_PAST)
        } else {
            ratingRepository.getRatingStream(Clock.System.now() - it.duration)
        }
    }.map { UiState.Success(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading
    )

    fun selectChip(option: ChipOptions) {
        chipState.update {
            option
        }
    }

}

enum class ChipOptions(val duration: Duration) {
    PAST_MONTH(30.toDuration(DurationUnit.DAYS)),
    PAST_YEAR(365.toDuration(DurationUnit.DAYS)),
    ALL_TIME(0.toDuration(DurationUnit.DAYS)),
}