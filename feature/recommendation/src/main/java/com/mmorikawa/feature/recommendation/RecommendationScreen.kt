package com.mmorikawa.feature.recommendation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.bookFeed


@Composable
fun RecommendationScreen() {
    // TODO: Replace with actual data
    val books = getFakeUserBookData(20)

    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
        bookFeed(
            feedItems = books,
            leadingContent = {
                AsyncImage(
                    model = it.bookCoverUrl,
                    contentDescription = "${it.title} book cover image"
                )
            },
            headlineContent = { Text(it.title) },
            supportingContent = { Text(it.genre) },
            overlineContent = { Text(it.author) }
        )
    }
}
