package com.mmorikawa.feature.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.model.Rating
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper

@Composable
internal fun HistoryRoute(
    onBookClick: (Int) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val ratingFeedState by viewModel.ratingFeedUiState.collectAsStateWithLifecycle()
    val optionState by viewModel.chipState.collectAsStateWithLifecycle()
    HistoryScreen(
        ratingFeedState,
        optionState,
        onOptionClick = { x: Int -> viewModel.selectChip(x) },
        onBookClick = onBookClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    ratingFeedState: UiState<List<Rating>>,
    optionState: ChipOptions,
    onOptionClick: (Int) -> Unit,
    onBookClick: (Int) -> Unit
) {

    // TODO: Extract to string resources
    val options = listOf("This Month", "This Year", "All Time")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO: Maybe extract this to DesignSystem
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { onOptionClick(index) },
                    selected = optionState.ordinal == index, /* TODO: Be able to select the button} */
                ) {
                    Text(text = label, maxLines = 1)
                }
            }
        }
        UiStateWrapper(uiState = ratingFeedState) { feed ->
            if (feed.isEmpty()) {
                HistoryEmpty()
            } else {
                LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
                    items(items = feed, key = { item -> item.book.id }) { rating ->
                        ListItem(
                            modifier = Modifier.clickable { onBookClick(rating.book.id) },
                            leadingContent = {
                                AsyncImage(
                                    model = rating.book.coverUrl,
                                    contentDescription = "${rating.book.title} book cover image"
                                )
                            },
                            overlineContent = { Text(rating.book.authors.toString()) },
                            headlineContent = { Text(rating.book.title) },
                            supportingContent = { Text("Rating: %.1f / 5".format(rating.score)) },
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun HistoryEmpty() {
    Box {
        Text("No books in reading history.")
    }
}