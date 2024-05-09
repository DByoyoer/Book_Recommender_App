package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.book_recommender.core.common.dispatchers.BookRecDispatchers
import com.mmorikawa.book_recommender.core.common.dispatchers.Dispatcher
import com.mmorikawa.book_recommender.core.database.dao.ReadingListDao
import com.mmorikawa.book_recommender.core.database.model.asEntity
import com.mmorikawa.book_recommender.core.database.model.asExternalModel
import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstReadingListRepository @Inject constructor(
    private val network: BookRecNetworkDataSource,
    private val readingListDao: ReadingListDao,
    @Dispatcher(BookRecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ReadingListRepository {
    override suspend fun getReadingList(orderByDateAdded: Boolean): List<ReadingListEntry> =
        withContext(ioDispatcher) {
            readingListDao.getReadingListBooks(orderByDateAdded = false)
                .map { it.asExternalModel() }
        }

    override suspend fun addReadingListEntry(readingListEntry: ReadingListEntry) =
        withContext(ioDispatcher) { readingListDao.upsertReadingListEntry(readingListEntry.asEntity()) }

    override suspend fun updateReadingListEntryRanking(readingListEntry: ReadingListEntry) =
        withContext(ioDispatcher) { readingListDao.upsertReadingListEntry(readingListEntry.asEntity()) }

    override suspend fun deleteReadingListEntry(bookIds: List<Int>) =
        withContext(ioDispatcher) { readingListDao.deleteReadingListEntriesByIds(bookIds) }
}


