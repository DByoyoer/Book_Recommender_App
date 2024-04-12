package com.mmorikawa.book_recommender.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "genre",
)
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String
)