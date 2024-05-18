package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.book_recommender.core.common.dispatchers.BookRecDispatchers
import com.mmorikawa.book_recommender.core.common.dispatchers.Dispatcher
import com.mmorikawa.book_recommender.core.data.model.asNetworkModel
import com.mmorikawa.book_recommender.core.database.dao.RatingDao
import com.mmorikawa.book_recommender.core.database.model.PopulatedRating
import com.mmorikawa.book_recommender.core.database.model.asEntity
import com.mmorikawa.book_recommender.core.database.model.asExternalModel
import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.core.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import javax.inject.Inject

class OfflineFirstRatingRepository @Inject constructor(
    private val ratingDao: RatingDao,
    private val networkDataSource: BookRecNetworkDataSource,
    @Dispatcher(BookRecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : RatingRepository {
    override fun getRatingStream(bookId: Int): Flow<Rating> =
        ratingDao.observeRating(bookId).map(PopulatedRating::asExternalModel)


    override suspend fun getRatings(): List<Rating> = withContext(ioDispatcher) {
        ratingDao.getRatings().map(PopulatedRating::asExternalModel)
    }

    override fun getRatingListStream(timeCutoff: Instant): Flow<List<Rating>> =
        ratingDao.observeAllRatings(timeCutoff).map { it.map(PopulatedRating::asExternalModel) }

    override suspend fun createRating(rating: Rating) = withContext(ioDispatcher) {
        ratingDao.upsertRating(rating.asEntity())
        networkDataSource.createRating(rating.asNetworkModel())
    }

    override suspend fun updateRating(rating: Rating) = withContext(ioDispatcher) {
        ratingDao.upsertRating(rating.asEntity())
        networkDataSource.updateRating(rating.asNetworkModel())
    }

    override suspend fun deleteRating(ids: List<Int>) = withContext(ioDispatcher) {
        ratingDao.deleteRatingsByIds(ids)
        for (id in ids) {
            networkDataSource.deleteRating(id)
        }
    }
}