package com.mmorikawa.book_recommender.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mmorikawa.book_recommender.core.database.dao.BookDao
import com.mmorikawa.book_recommender.core.database.dao.RatingDao
import com.mmorikawa.book_recommender.core.database.dao.ReadingListDao
import com.mmorikawa.book_recommender.core.database.model.AuthorEntity
import com.mmorikawa.book_recommender.core.database.model.BookAuthorAssociation
import com.mmorikawa.book_recommender.core.database.model.BookEntity
import com.mmorikawa.book_recommender.core.database.model.BookGenreAssociation
import com.mmorikawa.book_recommender.core.database.model.GenreEntity
import com.mmorikawa.book_recommender.core.database.model.RatingEntity
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity
import com.mmorikawa.book_recommender.core.database.util.InstantConverter


@Database(
    entities = [
        BookEntity::class,
        AuthorEntity::class,
        RatingEntity::class,
        ReadingListEntity::class,
        BookAuthorAssociation::class,
        GenreEntity::class,
        BookGenreAssociation::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class BookRecDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun ratingDao(): RatingDao
    abstract fun readingListDao(): ReadingListDao
}