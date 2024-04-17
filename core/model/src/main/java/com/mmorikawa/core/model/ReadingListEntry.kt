package com.mmorikawa.core.model

import kotlinx.datetime.Instant

data class ReadingListEntry(
    val bookId: Int,
    val title: String,
    val genres: List<String>,
    val authors: List<String>,
    val dateAdded: Instant,
    val ranking: Int
)