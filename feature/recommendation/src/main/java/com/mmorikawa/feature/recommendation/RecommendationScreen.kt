package com.mmorikawa.feature.recommendation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
internal fun RecommendationRoute(
    onBookClick: (Int) -> Unit,
    viewModel: RecommendationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RecommendationScreen(onBookClick = onBookClick, state = uiState)

}

@Composable
fun RecommendationScreen(
    onBookClick: (Int) -> Unit,
    state: UiState<List<SimpleBook>>
) {
    UiStateWrapper(uiState = state) { feed ->
        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
            items(items = feed, key = { item -> item.id }) { simpleBook ->
                ListItem(
                    modifier = Modifier.clickable { onBookClick(simpleBook.id) },
                    leadingContent = {
                        AsyncImage(
                            model = simpleBook.coverUrl,
                            contentDescription = "${simpleBook.title} book cover image"
                        )
                    },
                    headlineContent = { Text(simpleBook.title) },
                    supportingContent = { Text(simpleBook.genres.toString()) },
                    overlineContent = { Text(simpleBook.authors.toString()) })
            }
        }
    }

}
