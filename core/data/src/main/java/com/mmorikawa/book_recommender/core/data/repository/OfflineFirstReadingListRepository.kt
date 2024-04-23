package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.book_recommender.core.database.dao.ReadingListDao
import com.mmorikawa.book_recommender.core.database.model.asEntity
import com.mmorikawa.book_recommender.core.database.model.asExternalModel
import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.core.model.ReadingListEntry
import javax.inject.Inject

class OfflineFirstReadingListRepository @Inject constructor(
    private val network: BookRecNetworkDataSource,
    private val readingListDao: ReadingListDao
) : ReadingListRepository {
    override suspend fun getReadingList(orderByDateAdded: Boolean): List<ReadingListEntry> =
        readingListDao.getReadingListBooks(orderByDateAdded = false).map { it.asExternalModel() }

    override suspend fun addReadingListEntry(readingListEntry: ReadingListEntry) =
        readingListDao.upsertReadingListEntry(readingListEntry.asEntity())

    override suspend fun updateReadingListEntryRanking(readingListEntry: ReadingListEntry) =
        readingListDao.upsertReadingListEntry(readingListEntry.asEntity())

    override suspend fun deleteReadingListEntry(bookIds: List<Int>) =
        readingListDao.deleteReadingListEntriesByIds(bookIds)
}


