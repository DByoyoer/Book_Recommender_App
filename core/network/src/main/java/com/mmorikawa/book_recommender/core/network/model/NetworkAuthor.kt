package com.mmorikawa.book_recommender.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkAuthor(
    val id: Int,
    val name: String,
    val books: List<NetworkBook> = emptyList()
)
