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
    constructor(bookDetailed: BookDetailed, userData: UserData) : this(
        isbn = bookDetailed.isbn,
        title = bookDetailed.title,
        genres = bookDetailed.genres,
        bookCoverUrl = bookDetailed.coverUrl,
        authors = bookDetailed.authors,
        isOnReadingList = userData.readingList.contains(bookDetailed.isbn),
        hasBeenRead = userData.readBooks.contains(bookDetailed.isbn)
    )


}
