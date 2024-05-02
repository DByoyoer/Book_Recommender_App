package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.Rating

class OfflineFirstRatingRepository : RatingRepository {
    override suspend fun getRatings(): List<Rating> {
        TODO("Not yet implemented")
    }

    override suspend fun createRating(rating: Rating) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRating(rating: Rating) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRating(rating: Rating) {
        TODO("Not yet implemented")
    }
}