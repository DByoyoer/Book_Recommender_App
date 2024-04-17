package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.ReadingListEntry

interface ReadingListRepository {

    suspend fun getReadingList(): List<ReadingListEntry>

    suspend fun addReadingListEntry(bookId: Int, ranking: Int)

    suspend fun updateReadingListEntryRanking(readingListEntry: ReadingListEntry)

    suspend fun deleteReadingListEntry(readingListEntry: ReadingListEntry)

}