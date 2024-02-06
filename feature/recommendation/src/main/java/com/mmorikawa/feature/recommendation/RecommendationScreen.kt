package com.mmorikawa.feature.recommendation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mmorikawa.core.model.BookInfo
import com.mmorikawa.core.model.UserBookInfo
import com.mmorikawa.core.model.UserData
import com.mmorikawa.core.ui.bookFeed


@Composable
fun RecommendationScreen() {
    // TODO: Replace with actual data
    val books = mutableListOf<UserBookInfo>()
    for (i in 1..25) {
        books.add(
            UserBookInfo(
                bookInfo = BookInfo(
                    title = "Book Title$i",
                    author = "Author Lastname$i",
                    isbn = "000000$i",
                    bookCoverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg",
                    genre = "Fantasy"
                ),
                userData = UserData(
                    readBooks = setOf("0000005"),
                    readingList = setOf("0000004", "0000006")
                )
            )
        )
    }

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
