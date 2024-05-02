package com.mmorikawa.book_recommender.core.data.di

import com.mmorikawa.book_recommender.core.data.repository.BookRepository
import com.mmorikawa.book_recommender.core.data.repository.OfflineFirstBookRepository
import com.mmorikawa.book_recommender.core.data.repository.OfflineFirstRatingRepository
import com.mmorikawa.book_recommender.core.data.repository.OfflineFirstReadingListRepository
import com.mmorikawa.book_recommender.core.data.repository.RatingRepository
import com.mmorikawa.book_recommender.core.data.repository.ReadingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBookRepository(
        bookRepository: OfflineFirstBookRepository
    ): BookRepository

    @Binds
    fun bindsReadingListRepository(
        readingListRepository: OfflineFirstReadingListRepository
    ): ReadingListRepository

    @Binds
    fun bindsRatingRepository(
        ratingRepository: OfflineFirstRatingRepository
    ): RatingRepository
}