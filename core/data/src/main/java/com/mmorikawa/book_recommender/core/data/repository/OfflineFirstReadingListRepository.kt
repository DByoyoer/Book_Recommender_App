package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.book_recommender.core.common.dispatchers.BookRecDispatchers
import com.mmorikawa.book_recommender.core.common.dispatchers.Dispatcher
import com.mmorikawa.book_recommender.core.database.dao.ReadingListDao
import com.mmorikawa.book_recommender.core.database.model.PopulatedReadingListEntity
import com.mmorikawa.book_recommender.core.database.model.asEntity
import com.mmorikawa.book_recommender.core.database.model.asExternalModel
import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.core.model.ReadingListEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstReadingListRepository @Inject constructor(
    private val network: BookRecNetworkDataSource,
    private val readingListDao: ReadingListDao,
    @Dispatcher(BookRecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ReadingListRepository {
    override suspend fun getReadingList(
        orderByDateAdded: Boolean,
        ascending: Boolean
    ): List<ReadingListEntry> =
        withContext(ioDispatcher) {
            readingListDao.getReadingListBooks(orderByDateAdded = false)
                .map(PopulatedReadingListEntity::asExternalModel)
        }

    override fun getReadingListStream(
        orderByDateAdded: Boolean,
        ascending: Boolean
    ): Flow<List<ReadingListEntry>> =
        readingListDao.observerAllReadingListBooks(orderByDateAdded).map {
            if (ascending) {
                it.map(PopulatedReadingListEntity::asExternalModel)
            } else {
                it.map(PopulatedReadingListEntity::asExternalModel).asReversed()
            }
        }

    override suspend fun addReadingListEntry(readingListEntry: ReadingListEntry) =
        withContext(ioDispatcher) { readingListDao.upsertReadingListEntry(readingListEntry.asEntity()) }

    override suspend fun updateReadingListEntryRanking(readingListEntry: ReadingListEntry) =
        withContext(ioDispatcher) { readingListDao.upsertReadingListEntry(readingListEntry.asEntity()) }

    override suspend fun deleteReadingListEntry(bookIds: List<Int>) =
        withContext(ioDispatcher) { readingListDao.deleteReadingListEntriesByIds(bookIds) }
}


