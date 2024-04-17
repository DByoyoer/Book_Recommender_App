package com.mmorikawa.book_recommender.core.data.repository

import com.mmorikawa.core.model.BookDetailed
import com.mmorikawa.core.model.BookSimple

interface BookRepository {
    suspend fun getSimpleBookById(id: Int): BookSimple

    suspend fun getSimpleBooksByIds(ids: List<Int>): List<BookSimple>

    suspend fun getDetailedBookById(id: Int): BookDetailed
}