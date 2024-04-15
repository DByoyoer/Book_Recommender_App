package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo

data class BasicBook(
    val id: Int,
    val title: String,
    @ColumnInfo(name = "cover_url")
    val coverUrl: String,
)
