package com.mmorikawa.feature.history

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.bookFeed

@Composable
fun HistoryScreen() {
    // TODO: Use actual data, this is also the incorrect type of data
    val books = getFakeUserBookData()
    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
        bookFeed(
            feedItems = books,
            leadingContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = it.bookCoverUrl,
                        contentDescription = "${it.title} book cover image"
                    )
                }
            },
            headlineContent = { Text(it.title) },
            supportingContent = { Text("Rated: 4/5 on 12/31/23") },
            overlineContent = { Text(it.author) }
        )
    }
}