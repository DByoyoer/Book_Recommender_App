package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.Rating
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface RatingRepository {
    suspend fun getRatings(): List<Rating>

    fun getRatingStream(timeCutoff: Instant): Flow<List<Rating>>

    suspend fun createRating(rating: Rating)

    suspend fun updateRating(rating: Rating)

    suspend fun deleteRating(ids: List<Int>)
}