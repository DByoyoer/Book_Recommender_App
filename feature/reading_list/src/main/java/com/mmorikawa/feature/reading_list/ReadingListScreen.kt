package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.model.ReadingListEntry
import com.mmorikawa.core.ui.DragDropColumn
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper

@Composable
internal fun ReadingListRoute(
    viewModel: ReadingListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ReadingListScreen(uiState, viewModel::changeRankViaIndex)
}

@Composable
fun ReadingListScreen(state: UiState<List<ReadingListEntry>>, onSwap: (Int, Int) -> Unit) {
    UiStateWrapper(uiState = state) { feed ->
        if (feed.isEmpty()) {
            emptyReadingList()
        } else {
            DragDropColumn(items = feed, onSwap = onSwap) { entry ->
                ListItem(leadingContent = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(entry.ranking.toString(), fontSize = 24.sp)
                        AsyncImage(
                            model = entry.book.coverUrl,
                            contentDescription = "${entry.book.title} book cover image"
                        )
                    }
                },
                    headlineContent = { Text(entry.book.title) },
                    supportingContent = { Text(entry.book.genres.toString()) },
                    overlineContent = { Text(entry.book.authors.toString()) })
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