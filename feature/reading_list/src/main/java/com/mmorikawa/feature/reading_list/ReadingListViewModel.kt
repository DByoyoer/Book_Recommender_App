package com.mmorikawa.feature.reading_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.ReadingListRepository
import com.mmorikawa.core.model.ReadingListEntry
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReadingListViewModel @Inject constructor(
    val readingListRepository: ReadingListRepository
) : ViewModel() {
    // TODO: Set up repository
    val _sortState = MutableStateFlow<Pair<SortValue, SortOrder>>(
        Pair(
            SortValue.DATE_ADDED,
            SortOrder.ASCENDING
        )
    )
    val uiState: StateFlow<UiState<List<ReadingListEntry>>> =
        _sortState.flatMapLatest { sortOptions ->
            readingListRepository.getReadingListStream(
                orderByDateAdded = sortOptions.first == SortValue.DATE_ADDED,
                ascending = sortOptions.second == SortOrder.ASCENDING
            ).map { UiState.Success(it) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    fun toggleOrder() {
        _sortState.update {
            Pair(
                it.first,
                if (it.second == SortOrder.ASCENDING) SortOrder.DESCENDING else SortOrder.ASCENDING
            )
        }
    }

    fun updateSortValue(value: SortValue) {
        _sortState.update { Pair(value, it.second) }
    }


}


enum class SortOrder {
    ASCENDING, DESCENDING
}

enum class SortValue {
    RANKING, DATE_ADDED
}
