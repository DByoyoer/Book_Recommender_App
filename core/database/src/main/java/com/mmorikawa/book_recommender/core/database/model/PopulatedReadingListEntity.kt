package com.mmorikawa.book_recommender.core.database.model

import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.datetime.Instant

data class PopulatedReadingListEntity(
    val book: PopulatedBasicBook,
    val dateAdded: Instant,
    val ranking: Int,
)

fun PopulatedReadingListEntity.asExternalModel() = ReadingListEntry(
    book = book.asExternalModel(),
    dateAdded = dateAdded,
    ranking = ranking
)