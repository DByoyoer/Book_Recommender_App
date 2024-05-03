package com.mmorikawa.feature.recommendation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mmorikawa.core.ui.BookFeedUiState
import com.mmorikawa.core.ui.bookFeed


@Composable
internal fun RecommendationRoute(
    viewModel: RecommendationViewModel = hiltViewModel()
) {
    RecommendationScreen(feedState = viewModel.uiState)

}

@Composable
fun RecommendationScreen(
    feedState: BookFeedUiState
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
        bookFeed(feedState,
            leadingContent = { userBookInfo, _ ->
                AsyncImage(
                    model = userBookInfo.bookCoverUrl,
                    contentDescription = "${userBookInfo.title} book cover image"
                )
            },
            headlineContent = { Text(it.title) },
            supportingContent = { Text(it.genres.toString()) },
            overlineContent = { Text(it.authors.toString()) })
    }
}
