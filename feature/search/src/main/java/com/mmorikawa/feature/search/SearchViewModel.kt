package com.mmorikawa.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.asUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var queryText by mutableStateOf("")
        private set
    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResult: StateFlow<UiState<List<SimpleBook>>> = searchQuery.flatMapLatest { query ->
        if (query.length < 2) {
            flowOf(UiState.Empty(""))
        } else {
            bookRepository.searchBooks(query).asUiState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading,
    )

    fun onSearch(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onUpdateQuery(query: String) {
        queryText = query
    }

}

private const val SEARCH_QUERY = "searchQuery"