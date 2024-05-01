package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mmorikawa.core.ui.BookFeedUiState
import com.mmorikawa.core.ui.bookFeed

@Composable
internal fun ReadingListRoute(
    viewModel: ReadingListViewModel = hiltViewModel()
) {
    ReadingListScreen(feedState = viewModel.feedUiState)
}

@Composable
fun ReadingListScreen(feedState: BookFeedUiState) {
    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
        bookFeed(
            feedState = feedState,
            leadingContent = { userBookInfo, index: Int ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // TODO: Once ranking is implemented fix this
                    Text(text = "${index + 1}", modifier = Modifier.padding(horizontal = 16.dp))
                    AsyncImage(
                        model = userBookInfo.bookCoverUrl,
                        contentDescription = "${userBookInfo.title} book cover image"
                    )
                }
            },
            headlineContent = { Text(it.title) },
            supportingContent = { Column { Text(it.genres.toString()) } },
            overlineContent = { Text(it.authors.toString()) }
        )
    }
}