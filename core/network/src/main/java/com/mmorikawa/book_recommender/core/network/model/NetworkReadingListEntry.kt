package com.mmorikawa.book_recommender.core.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReadingListEntry(
    @SerialName("book_id")
    val bookId: Int,

    @SerialName("ranking")
    val ranking: Int,

    @SerialName("date_added")
    val dateAdded: Instant
)
