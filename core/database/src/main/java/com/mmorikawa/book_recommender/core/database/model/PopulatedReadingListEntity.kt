package com.mmorikawa.book_recommender.core.database.model

import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.datetime.Instant

data class PopulatedReadingListEntity(
    val book: PopulatedBasicBook,
    val dateAdded: Instant,
    val ranking: Int,
)

fun PopulatedReadingListEntity.asExternalModel() = ReadingListEntry(
    bookId = book.basicBook.id,
    title = book.basicBook.title,
    genres = book.genres.map { it.name },
    authors = book.authors.map { it.name },
    dateAdded = dateAdded,
    ranking = ranking
)