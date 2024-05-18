package com.mmorikawa.book_recommender.feature.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.book_recommender.feature.book_detail.navigation.BookDetailArgs
import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository
) : ViewModel() {
    private val bookDetailArgs: BookDetailArgs = BookDetailArgs(savedStateHandle)
    private val bookId = bookDetailArgs.bookId
    val bookDetailUiState: StateFlow<UiState<DetailedBook>> =
        bookRepository.getDetailedBookStream(bookId).map { UiState.Success(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

}