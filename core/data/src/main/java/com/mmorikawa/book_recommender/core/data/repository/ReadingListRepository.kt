package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.ReadingListEntry

interface ReadingListRepository {

    suspend fun getReadingList(orderByDateAdded: Boolean): List<ReadingListEntry>

    suspend fun addReadingListEntry(readingListEntry: ReadingListEntry)

    suspend fun updateReadingListEntryRanking(readingListEntry: ReadingListEntry)

    suspend fun deleteReadingListEntry(bookIds: List<Int>)

}