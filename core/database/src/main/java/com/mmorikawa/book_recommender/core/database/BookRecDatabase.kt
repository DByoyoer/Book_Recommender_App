package com.mmorikawa.book_recommender.core.database

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
    version = 1
)
abstract class BookRecDatabase {

}