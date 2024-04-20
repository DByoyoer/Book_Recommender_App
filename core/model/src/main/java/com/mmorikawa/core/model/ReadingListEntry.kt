package com.mmorikawa.core.model

import kotlinx.datetime.Instant

data class ReadingListEntry(
    val book: SimpleBook,
    val dateAdded: Instant,
    val ranking: Int
)