package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "book"
)
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    @ColumnInfo(name = "cover_url", defaultValue = "")
    val coverUrl: String,
    @ColumnInfo(defaultValue = "")
    val isbn: String,
    @ColumnInfo(defaultValue = "")
    val isbn13: String,
    @ColumnInfo(name = "lang_code", defaultValue = "")
    val langCode: String,
    @ColumnInfo(defaultValue = "0")
    val pages: Int,
    @ColumnInfo(name = "original_publication_year", defaultValue = "0")
    val originalPublicationYear: Int,
)