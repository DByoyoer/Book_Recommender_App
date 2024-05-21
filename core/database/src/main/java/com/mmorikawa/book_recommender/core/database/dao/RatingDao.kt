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

    @Transaction
    @Query(
        "SELECT book.id, book.title, book.cover_url, rating.* " +
                "FROM rating " +
                "INNER JOIN book ON rating.book_id = book.id " +
                "WHERE rating.book_id = :bookId"
    )
    fun observeRating(bookId: Int): Flow<PopulatedRating>

    // TODO: Add order options
    @Transaction
    @Query(
        "SELECT book.id, book.title, book.cover_url, rating.* " +
                "FROM rating " +
                "INNER JOIN book ON rating.book_id = book.id " +
                "ORDER BY rating.date_updated"
    )
    suspend fun getRatings(): List<PopulatedRating>

    @Transaction
    @Query(
        "SELECT book.id, book.title, book.cover_url, rating.* " +
                "FROM rating " +
                "INNER JOIN book ON rating.book_id = book.id " +
                "WHERE rating.date_updated >= :timeCutoff " +
                "ORDER BY rating.date_updated "
    )
    fun observeAllRatings(timeCutoff: Instant): Flow<List<PopulatedRating>>

    @Query("DELETE FROM rating WHERE book_id in (:bookIds)")
    fun deleteRatingsByIds(bookIds: List<Int>)

    @Transaction
    @Query("SELECT * FROM rating WHERE book_id = :bookId")
    fun getRatingByBookId(bookId: Int): PopulatedRating
}