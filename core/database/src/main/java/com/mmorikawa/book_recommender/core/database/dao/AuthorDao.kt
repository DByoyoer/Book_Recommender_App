package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mmorikawa.book_recommender.core.database.model.AuthorEntity

@Dao
interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreAuthors(authors: List<AuthorEntity>): List<Long>
}