package com.mmorikawa.book_recommender.core.network

import com.mmorikawa.book_recommender.core.network.model.NetworkAuthor
import com.mmorikawa.book_recommender.core.network.model.NetworkBook
import com.mmorikawa.book_recommender.core.network.model.NetworkRating
import com.mmorikawa.book_recommender.core.network.model.NetworkReadingListEntry

interface BookRecNetworkDataSource {
    suspend fun getBook(bookId: Int): NetworkBook
    suspend fun getBookRecs(): List<NetworkBook>
    suspend fun getReadingList(): List<NetworkReadingListEntry>
    suspend fun getRatings(): List<NetworkRating>
    suspend fun deleteRating(bookId: Int)
    suspend fun getAuthor(authorId: Int): NetworkAuthor
    suspend fun createRating(rating: NetworkRating)
    suspend fun createReadingListEntry(readingListEntry: NetworkReadingListEntry)
    suspend fun updateRating(rating: NetworkRating)
    suspend fun updateReadingListEntry(readingListEntry: NetworkReadingListEntry)
    suspend fun getTopNBooks(n: Int): List<NetworkBook>
}