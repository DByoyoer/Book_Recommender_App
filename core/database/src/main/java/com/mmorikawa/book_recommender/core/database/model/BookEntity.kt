package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "books"
)
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    @ColumnInfo(name = "cover_url", defaultValue = "")
    val coverImageUrl: String
)