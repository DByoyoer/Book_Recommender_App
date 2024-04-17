package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntry

@Dao
interface ReadingListDao {
    @Upsert
    suspend fun upsertReadingListEntry(readingListEntry: ReadingListEntity)

    @Transaction
    @Query(
        "SELECT book.id, book.title, book.cover_url, reading_list.ranking, reading_list.date_added " +
                "FROM reading_list " +
                "INNER JOIN book ON reading_list.book_id = book.id " +
                "ORDER BY " +
                "CASE WHEN :orderByDateAdded = 1 THEN reading_list.date_added ELSE reading_list.ranking END ASC"
    )
    suspend fun getReadingListBooks(orderByDateAdded: Boolean): List<ReadingListEntry>

    @Query("DELETE FROM reading_list WHERE book_id in (:bookIds)")
    fun deleteReadingListEntriesByIds(bookIds: List<Int>)
}