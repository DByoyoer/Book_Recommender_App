package com.mmorikawa.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import coil.compose.AsyncImage
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.BookInfo

fun LazyGridScope.bookFeed(
    feedItems: List<BookInfo>
) {
    items(items = feedItems, key = { it.isbn }) { bookInfo ->
        ListItem(
            leadingContent = {
                AsyncImage(model = bookInfo.bookCoverUrl, contentDescription = "")
            },
            headlineContent = { Text(bookInfo.title) },
            overlineContent = { Text(bookInfo.author) },
            supportingContent = { Text(bookInfo.genre) },
            trailingContent = { Icon(BookRecIcons.MoreVert, contentDescription = null) }
        )
        Divider()
    }
}