package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.model.ReadingListEntry
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper

@Composable
internal fun ReadingListRoute(
    viewModel: ReadingListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ReadingListScreen(uiState)
}

@Composable
fun ReadingListScreen(state: UiState<List<ReadingListEntry>>) {
    UiStateWrapper(uiState = state) { feed ->
        if (feed.isEmpty()) {
            emptyReadingList()
        } else {
            LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
                items(items = feed, key = { item -> item.book.id }) { entry ->
                    ListItem(leadingContent = {
                        AsyncImage(
                            model = entry.book.coverUrl,
                            contentDescription = "${entry.book.title} book cover image"
                        )
                    },
                        headlineContent = { Text(entry.book.title) },
                        supportingContent = { Text(entry.book.genres.toString()) },
                        overlineContent = { Text(entry.book.authors.toString()) })
                }
            }
        }
    }
}

@Composable
fun emptyReadingList() {
    Box {
        Text("No books in your reading list")
    }
}