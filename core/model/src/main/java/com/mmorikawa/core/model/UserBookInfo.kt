package com.mmorikawa.core.model

data class UserBookInfo internal constructor(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val bookCoverUrl: String,
    val authors: List<String>,
    val isOnReadingList: Boolean,
    val hasBeenRead: Boolean,

    ) {
    constructor(simpleBook: SimpleBook, userData: UserData) : this(
        id = simpleBook.id,
        title = simpleBook.title,
        genres = simpleBook.genres,
        bookCoverUrl = simpleBook.coverUrl,
        authors = simpleBook.authors,
        isOnReadingList = userData.readingList.contains(simpleBook.id),
        hasBeenRead = userData.readBooks.contains(simpleBook.id)
    )


}
