package com.mmorikawa.core.testing.data

import com.mmorikawa.core.model.BookInfo
import com.mmorikawa.core.model.UserBookInfo
import com.mmorikawa.core.model.UserData

fun getFakeUserBookData(n: Int = 10): MutableList<UserBookInfo> {
    val books = mutableListOf<UserBookInfo>()
    for (i in 1..n) {
        books.add(
            UserBookInfo(
                bookInfo = BookInfo(
                    title = "Book Title $i",
                    author = "Author Lastname$i",
                    isbn = "000000$i",
                    bookCoverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg",
                    genre = "Fantasy"
                ), userData = UserData(
                    readBooks = setOf("0000005"), readingList = setOf("0000004", "0000006")
                )
            )
        )
    }
    return books
}