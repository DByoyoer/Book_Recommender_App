package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.datetime.Instant

data class PopulatedReadingListEntity(
    @Embedded
    val book: PopulatedBasicBook,
    @ColumnInfo(name = "date_added")
    val dateAdded: Instant,
    val ranking: Int,
)

fun PopulatedReadingListEntity.asExternalModel() = ReadingListEntry(
    book = book.asExternalModel(),
    dateAdded = dateAdded,
    ranking = ranking
)

fun ReadingListEntry.asEntity(): ReadingListEntity = ReadingListEntity(
    bookId = book.id,
    ranking = ranking,
    dateAdded = dateAdded
)