package com.mmorikawa.book_recommender.core.database.di

import com.mmorikawa.book_recommender.core.database.BookRecDatabase
import com.mmorikawa.book_recommender.core.database.dao.BookDao
import com.mmorikawa.book_recommender.core.database.dao.RatingDao
import com.mmorikawa.book_recommender.core.database.dao.ReadingListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesBookDao(
        database: BookRecDatabase
    ): BookDao = database.bookDao()

    @Provides
    fun providesRatingDao(
        database: BookRecDatabase
    ): RatingDao = database.ratingDao()

    @Provides
    fun providesReadingListDao(
        database: BookRecDatabase
    ): ReadingListDao = database.readingListDao()
}