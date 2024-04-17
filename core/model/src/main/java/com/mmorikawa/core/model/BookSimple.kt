package com.mmorikawa.core.model

data class BookSimple(
    val id: Int,
    val title: String,
    val coverUrl: String,
    val authors: List<String> = listOf(),
    val genres: List<String> = listOf(),
)
