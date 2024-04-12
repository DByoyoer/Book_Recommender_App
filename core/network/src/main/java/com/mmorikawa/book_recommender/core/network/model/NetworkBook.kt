package com.mmorikawa.book_recommender.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkBook(
    val id: Int,
    val title: String,
    val description: String = "",
    val isbn: String = "",
    val isbn13: String = "",
    val coverUrl: String = "",
    val langCode: String = "",
    val originalPublicationYear: Int = -1,
    val pages: Int = -1,
    val authors: List<NetworkAuthor> = listOf(),
    val genres: List<NetworkGenre> = listOf(),
)


@Serializable
data class NetworkGenre(
    val id: Int, val name: String
)