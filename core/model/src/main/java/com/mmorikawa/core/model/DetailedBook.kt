package com.mmorikawa.core.model

data class DetailedBook(
    val id: Int,
    val title: String,
    val description: String,
    val isbn: String,
    val isbn13: String,
    val coverUrl: String,
    val langCode: String,
    val originalPublicationYear: Int,
    val pages: Int,
    val authors: List<String>,
    val genres: List<String>,
)
