package com.mmorikawa.core.testing.data

import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.model.UserBookInfo
import com.mmorikawa.core.model.UserData

fun getFakeUserBookData(n: Int = 10): MutableList<UserBookInfo> {
    val books = mutableListOf<UserBookInfo>()
    for (i in 1..n) {
        books.add(
            UserBookInfo(
                simpleBook = SimpleBook(
                    id = i,
                    title = "Book Title $i",
                    authors = listOf("Author Lastname$i"),
                    coverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg",
                    genres = listOf("Fantasy"),
                ), userData = UserData(
                    readBooks = setOf(223), readingList = setOf(22, 29)
                )
            )
        )
    }
    return books
}