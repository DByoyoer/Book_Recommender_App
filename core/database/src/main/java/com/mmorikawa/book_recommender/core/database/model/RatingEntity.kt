package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "rating",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["book_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RatingEntity(
    @ColumnInfo(name = "book_id")
    val bookId: Int,
    val score: Int,
    @ColumnInfo(name = "rating_text")
    val ratingText: String,
    @ColumnInfo(name = "date_created")
    val dateCreated: Instant
)
