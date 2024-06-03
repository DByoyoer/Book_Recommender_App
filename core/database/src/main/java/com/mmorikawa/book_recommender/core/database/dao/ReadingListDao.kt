package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.PopulatedReadingListEntity
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity
import kotlinx.coroutines.flow.Flow

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
    suspend fun getReadingListBooks(orderByDateAdded: Boolean): List<PopulatedReadingListEntity>

    @Transaction
    @Query(
        "SELECT book.id, book.title, book.cover_url, reading_list.ranking, reading_list.date_added " +
                "FROM reading_list " +
                "INNER JOIN book ON reading_list.book_id = book.id " +
                "ORDER BY " +
                "CASE WHEN :orderByDateAdded = 1 THEN reading_list.date_added ELSE reading_list.ranking END ASC"
    )
    fun observerAllReadingListBooks(orderByDateAdded: Boolean): Flow<List<PopulatedReadingListEntity>>


    @Query("DELETE FROM reading_list WHERE book_id in (:bookIds)")
    fun deleteReadingListEntriesByIds(bookIds: List<Int>)

    @Query("SELECT MAX(reading_list.ranking) from reading_list")
    suspend fun getLastRanking(): Int

    @Query("UPDATE reading_list SET ranking = ranking - 1 WHERE ranking > :oldRank AND ranking <= :newRank")
    suspend fun updateRankingsIncrease(oldRank: Int, newRank: Int)

    @Query("UPDATE reading_list SET ranking = ranking + 1 WHERE ranking < :oldRank AND ranking >= :newRank")
    suspend fun updateRankingsDecrease(oldRank: Int, newRank: Int)

    @Query("UPDATE reading_list SET ranking = :newRank WHERE book_id = :bookId")
    fun setRank(bookId: Int, newRank: Int)

    @Query("SELECT * FROM reading_list where ranking = :rank LIMIT 1")
    suspend fun getEntryByRank(rank: Int): ReadingListEntity

    @Query("SELECT EXISTS (SELECT 1 FROM reading_list where book_id = :bookId)")
    suspend fun bookIdExists(bookId: Int): Boolean
}