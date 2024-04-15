package com.mmorikawa.book_recommender.core.database.di

import android.content.Context
import androidx.room.Room
import com.mmorikawa.book_recommender.core.database.BookRecDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesBookRecDatabase(@ApplicationContext context: Context): BookRecDatabase =
        Room.databaseBuilder(context, BookRecDatabase::class.java, "book-rec-database").build()
}