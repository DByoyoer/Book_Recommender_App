package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
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
    @PrimaryKey
    val bookId: Int,
    val score: Int,
    @ColumnInfo(name = "rating_text")
    val ratingText: String,
    @ColumnInfo(name = "date_created", defaultValue = "CURRENT_TIMESTAMP")
    val dateCreated: Instant,
    @ColumnInfo(name = "date_updated", defaultValue = "CURRENT_TIMESTAMP")
    val dateUpdated: Instant
)
