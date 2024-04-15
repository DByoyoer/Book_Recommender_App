package com.mmorikawa.core.model

data class UserBookInfo internal constructor(
    val isbn: String,
    val title: String,
    val genres: List<String>,
    val bookCoverUrl: String,
    val authors: List<String>,
    val isOnReadingList: Boolean,
    val hasBeenRead: Boolean,

    ) {
    constructor(bookInfo: BookInfo, userData: UserData) : this(
        isbn = bookInfo.isbn,
        title = bookInfo.title,
        genres = bookInfo.genres,
        bookCoverUrl = bookInfo.coverUrl,
        authors = bookInfo.authors,
        isOnReadingList = userData.readingList.contains(bookInfo.isbn),
        hasBeenRead = userData.readBooks.contains(bookInfo.isbn)
    )


}
