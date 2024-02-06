package com.mmorikawa.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.UserBookInfo

fun LazyGridScope.bookFeed(
    feedItems: List<UserBookInfo>,
    leadingContent: @Composable (UserBookInfo) -> Unit,
    headlineContent: @Composable (UserBookInfo) -> Unit,
    overlineContent: (@Composable (UserBookInfo) -> Unit)? = null,
    supportingContent: (@Composable (UserBookInfo) -> Unit)? = null,
    onTrailingActionClick: () -> Unit = {}

) {
    items(items = feedItems, key = { it.isbn }) { userBookInfo ->
        ListItem(
            leadingContent = {
                leadingContent(userBookInfo)
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
            }
        )
        Divider()
    }
}