package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {
    @Upsert
    suspend fun upsertReadingListEntry(readingListEntry: ReadingListEntity)

    // TODO: Add order options
    @Query("SELECT * FROM reading_list ORDER BY ranking ASC")
    fun getReadingList(): Flow<ReadingListEntity>

    @Query("DELETE FROM reading_list WHERE book_id in (:bookIds)")
    fun deleteRatingsByIds(bookIds: List<Int>)
}