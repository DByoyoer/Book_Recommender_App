package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mmorikawa.book_recommender.core.database.model.GenreEntity

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreGenres(genres: List<GenreEntity>)
}