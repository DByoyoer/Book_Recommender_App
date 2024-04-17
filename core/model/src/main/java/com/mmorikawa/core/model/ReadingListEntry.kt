package com.mmorikawa.core.model

import kotlinx.datetime.Instant

data class ReadingListEntry(
    val book: BookSimple,
    val dateAdded: Instant,
    val ranking: Int
)