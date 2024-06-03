package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    viewModel: ReadingListViewModel = hiltViewModel(), onBookClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ReadingListScreen(
        state = uiState, onSwap = viewModel::changeRankViaIndex, onBookClick = onBookClick
    )
}

@Composable
fun ReadingListScreen(
    state: UiState<List<ReadingListEntry>>, onSwap: (Int, Int) -> Unit, onBookClick: (Int) -> Unit
) {
    UiStateWrapper(uiState = state) { feed ->
        if (feed.isEmpty()) {
            emptyReadingList()
        } else {
            DragDropColumn(items = feed, onSwap = onSwap) { entry ->
                ListItem(
                    leadingContent = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("${entry.ranking}.", fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(20.dp))
                            AsyncImage(
                                model = entry.book.coverUrl,
                                alignment = Alignment.Center,
                                contentDescription = "${entry.book.title} book cover image"
                            )
                        }
                    },
                    headlineContent = { Text(entry.book.title) },
                    supportingContent = { Text(entry.book.genres.toString()) },
                    overlineContent = { Text(entry.book.authors.toString()) },
                    modifier = Modifier.clickable(onClick = { onBookClick(entry.book.id) })
                )
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