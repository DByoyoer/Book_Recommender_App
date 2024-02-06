package com.mmorikawa.core.model

data class UserBookInfo internal constructor(
    val isbn: String,
    val title: String,
    val genre: String,
    val bookCoverUrl: String,
    val author: String,
    val isOnReadingList: Boolean,
    val hasBeenRead: Boolean,

){
    constructor(bookInfo: BookInfo, userData: UserData) : this(
        isbn = bookInfo.isbn,
        title = bookInfo.isbn,
        genre = bookInfo.genre,
        bookCoverUrl = bookInfo.bookCoverUrl,
        author = bookInfo.author,
        isOnReadingList = userData.readingList.contains(bookInfo.isbn),
        hasBeenRead = userData.readBooks.contains(bookInfo.isbn)
    )


}
