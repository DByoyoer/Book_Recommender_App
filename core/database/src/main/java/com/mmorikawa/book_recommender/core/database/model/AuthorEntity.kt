package com.mmorikawa.book_recommender.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Note that this separate table is only necessary due to the potential future feature
 * of allowing users to follow authors.
 * */
@Entity(
    tableName = "author"
)
data class AuthorEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)
