package com.mmorikawa.book_recommender.core.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRating(
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("score")
    val score: Float,
    @SerialName("rating_text")
    val ratingText: String = "",
    @SerialName("date_created")
    val dateCreated: Instant,
    @SerialName("date_updated")
    val dateUpdated: Instant
)
