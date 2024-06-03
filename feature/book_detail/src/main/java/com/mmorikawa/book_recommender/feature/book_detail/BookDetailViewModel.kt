package com.mmorikawa.book_recommender.feature.book_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.book_recommender.core.data.repository.ReadingListRepository
import com.mmorikawa.book_recommender.feature.book_detail.navigation.BookDetailArgs
import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
    private val readingListRepository: ReadingListRepository
) : ViewModel() {
    private val bookDetailArgs: BookDetailArgs = BookDetailArgs(savedStateHandle)
    private val bookId = bookDetailArgs.bookId
    var isInReadingListState by mutableStateOf(false)
        private set

    val bookDetailUiState: StateFlow<UiState<DetailedBook>> =
        bookRepository.getDetailedBookStream(bookId).map { UiState.Success(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    init {
        viewModelScope.launch {
            isInReadingListState = readingListRepository.isInReadingList(bookId)
        }
    }

    fun addBookToReadingList() {
        viewModelScope.launch {
            readingListRepository.addReadingListEntryById(bookId)
            isInReadingListState = true
        }
    }

    fun removeBookFromReadingList() {
        viewModelScope.launch {
            readingListRepository.deleteReadingListEntry(listOf(bookId))
            isInReadingListState = false
        }
    }


}