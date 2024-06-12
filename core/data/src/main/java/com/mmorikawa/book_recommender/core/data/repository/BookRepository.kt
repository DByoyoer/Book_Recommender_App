package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.model.SimpleBook
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getSimpleBookStream(id: Int): Flow<SimpleBook>

    fun getSimpleBooksStream(ids: List<Int>): Flow<List<SimpleBook>>

    fun getDetailedBookStream(id: Int): Flow<DetailedBook>

    fun getDetailedBooksStream(ids: List<Int>): Flow<List<SimpleBook>>

    suspend fun getSimpleBookById(id: Int): SimpleBook

    suspend fun getSimpleBooksByIds(ids: List<Int>): List<SimpleBook>

    suspend fun getDetailedBookById(id: Int): DetailedBook


    fun getRecommendations(): Flow<List<SimpleBook>>

    fun searchBooks(query: String): Flow<List<SimpleBook>>
}