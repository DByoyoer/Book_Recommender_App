package com.mmorikawa.book_recommender.core.database.model

import com.mmorikawa.core.model.Rating
import kotlinx.datetime.Instant

data class PopulatedRating(
    val book: PopulatedBasicBook,
    val dateCreated: Instant,
    val dateUpdated: Instant,
    val score: Int,
    val ratingText: String,
)

fun PopulatedRating.asExternalModel() = Rating(
    book = book.asExternalModel(),
    dateCreated = dateCreated,
    dateUpdated = dateUpdated,
    score = score,
    ratingText = ratingText
)

