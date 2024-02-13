package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.BookFeedUiState
import com.mmorikawa.core.ui.bookFeed

@Composable
internal fun ReadingListRoute() {
    // TODO: Use actual data, this is also the incorrect type of data
    val books = getFakeUserBookData()
    ReadingListScreen(feedState = BookFeedUiState.Success(books))
}

@Composable
fun ReadingListScreen(feedState: BookFeedUiState) {
    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
        bookFeed(
            feedState = feedState,
            leadingContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // TODO: Once ranking is implemented fix this
                    Text(text = "1.", modifier = Modifier.padding(horizontal = 16.dp))
                    AsyncImage(
                        model = it.bookCoverUrl,
                        contentDescription = "${it.title} book cover image"
                    )
                }
            },
            headlineContent = { Text(it.title) },
            supportingContent = { Text(it.genre) },
            overlineContent = { Text(it.author) }
        )
    }
}