package com.mmorikawa.core.model

import kotlinx.datetime.Instant

data class Rating(
    val book: SimpleBook,
    val score: Int,
    val ratingText: String,
    val dateCreated: Instant,
    val dateUpdated: Instant,
)
