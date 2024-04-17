package com.mmorikawa.book_recommender.core.database.model

import kotlinx.datetime.Instant

data class ReadingListEntry(
    val book: PopulatedBasicBook,
    val dateAdded: Instant,
)