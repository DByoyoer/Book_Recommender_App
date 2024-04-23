package com.mmorikawa.core.model

data class DetailedBook(
    val id: Int,
    val title: String,
    val description: String = "",
    val isbn: String = "",
    val isbn13: String = "",
    val coverUrl: String,
    val langCode: String = "",
    val originalPublicationYear: Int = 0,
    val pages: Int = 0,
    val authors: List<String> = listOf(),
    val genres: List<String> = listOf(),
)
