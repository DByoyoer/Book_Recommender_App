package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.mmorikawa.core.model.Rating
import kotlinx.datetime.Instant

data class PopulatedRating(
    @Embedded
    val book: PopulatedBasicBook,
    @ColumnInfo(name = "date_created")
    val dateCreated: Instant,
    @ColumnInfo(name = "date_updated")
    val dateUpdated: Instant,
    val score: Int,
    @ColumnInfo(name = "rating_text")
    val ratingText: String,
)

fun PopulatedRating.asExternalModel() = Rating(
    book = book.asExternalModel(),
    dateCreated = dateCreated,
    dateUpdated = dateUpdated,
    score = score,
    ratingText = ratingText
)

