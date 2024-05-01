package com.mmorikawa.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.UserBookInfo

fun LazyGridScope.bookFeed(
    feedState: BookFeedUiState,
    leadingContent: @Composable (UserBookInfo, Int) -> Unit,
    headlineContent: @Composable (UserBookInfo) -> Unit,
    overlineContent: (@Composable (UserBookInfo) -> Unit)? = null,
    supportingContent: (@Composable (UserBookInfo) -> Unit)? = null,
    onTrailingActionClick: () -> Unit = {}

) {
    when (feedState) {
        BookFeedUiState.Loading -> Unit
        is BookFeedUiState.Success -> {
            itemsIndexed(
                items = feedState.feed,
                key = { _: Int, item: UserBookInfo -> item.id }) { index, userBookInfo ->
                ListItem(
                    leadingContent = {
                        leadingContent(userBookInfo, index)
                    },
                    headlineContent = { headlineContent(userBookInfo) },
                    overlineContent = {
                        overlineContent?.invoke(userBookInfo)
                    },
                    supportingContent = { supportingContent?.invoke(userBookInfo) },
                    trailingContent = {
                        IconButton(onClick = { onTrailingActionClick() }) {
                            Icon(imageVector = BookRecIcons.MoreVert, contentDescription = null)
                        }
                    })
                Divider()
            }
        }
    }
}

sealed interface BookFeedUiState {
    /**
     * The feed is still loading.
     */
    data object Loading : BookFeedUiState

    data class Success(
        val feed: List<UserBookInfo>,
    ) : BookFeedUiState
}