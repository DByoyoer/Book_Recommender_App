package com.mmorikawa.book_recommender.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import com.mmorikawa.book_recommender.core.database.model.AuthorBookCrossRef
import com.mmorikawa.book_recommender.core.database.model.AuthorEntity
import com.mmorikawa.book_recommender.core.database.model.BookEntity
import com.mmorikawa.book_recommender.core.database.model.RatingEntity
import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity


@Database(
    entities = [
        BookEntity::class,
        AuthorEntity::class,
        RatingEntity::class,
        ReadingListEntity::class,
        AuthorBookCrossRef::class
    ],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = DatabaseMigrations.Schema1to2::class),
        AutoMigration(from = 2, to = 3, spec = DatabaseMigrations.Schema2to3::class),
        AutoMigration(from = 2, to = 3)
    ]
)
abstract class BookRecDatabase {

}