package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.coroutines.flow.Flow

interface ReadingListRepository {

    suspend fun getReadingList(
        orderByDateAdded: Boolean, ascending: Boolean
    ): List<ReadingListEntry>

    fun getReadingListStream(
        orderByDateAdded: Boolean, ascending: Boolean
    ): Flow<List<ReadingListEntry>>

    suspend fun addReadingListEntry(readingListEntry: ReadingListEntry)

    suspend fun updateReadingListEntryRanking(oldRank: Int, newRank: Int)

    suspend fun deleteReadingListEntry(bookIds: List<Int>)

    suspend fun addReadingListEntryById(bookId: Int)

    suspend fun isInReadingList(bookId: Int): Boolean
}