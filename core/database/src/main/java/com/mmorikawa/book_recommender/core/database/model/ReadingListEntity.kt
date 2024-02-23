package com.mmorikawa.book_recommender.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "reading_list",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["book_id"]
        )
    ]
)
data class ReadingListEntity(
    @ColumnInfo(name = "book_id")
    @PrimaryKey
    val bookId: Int,
    val ranking: Int,
    @ColumnInfo(name = "date_added")
    val dateAdded: Instant
)