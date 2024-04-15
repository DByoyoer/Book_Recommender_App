package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.RatingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {

    @Upsert
    suspend fun upsertRating(rating: RatingEntity)

    // TODO: Add order options
    @Query("SELECT * FROM rating ORDER BY date_updated DESC")
    fun getRatings(): Flow<RatingEntity>

    @Query("DELETE FROM rating WHERE book_id in (:bookIds)")
    fun deleteRatingsByIds(bookIds: List<Int>)
}