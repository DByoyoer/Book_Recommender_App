package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.PopulatedRating
import com.mmorikawa.book_recommender.core.database.model.RatingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

@Dao
interface RatingDao {

    @Upsert
    suspend fun upsertRating(rating: RatingEntity)

    // TODO: Add order options
    @Transaction
    @Query("SELECT * FROM rating ORDER BY date_updated DESC")
    suspend fun getRatings(): List<PopulatedRating>

    @Transaction
    @Query("SELECT * FROM rating WHERE rating.date_updated >= :timeCutoff ORDER BY date_updated DESC")
    fun observeAllRatings(timeCutoff: Instant): Flow<List<PopulatedRating>>

    @Query("DELETE FROM rating WHERE book_id in (:bookIds)")
    fun deleteRatingsByIds(bookIds: List<Int>)
}