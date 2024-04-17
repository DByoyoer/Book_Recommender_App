package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.Rating

interface RatingRepository {
    suspend fun getRatings(): List<Rating>

    suspend fun createRating(rating: Rating)

    suspend fun updateRating(rating: Rating)

    suspend fun deleteRating(rating: Rating)
}