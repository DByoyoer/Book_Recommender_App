package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.model.SimpleBook

interface BookRepository {
    suspend fun getSimpleBookById(id: Int): SimpleBook

    suspend fun getSimpleBooksByIds(ids: List<Int>): List<SimpleBook>

    suspend fun getDetailedBookById(id: Int): DetailedBook

    suspend fun getRecommendations(): List<SimpleBook>
}