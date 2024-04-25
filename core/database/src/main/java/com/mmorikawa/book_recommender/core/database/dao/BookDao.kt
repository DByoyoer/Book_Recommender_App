package com.mmorikawa.book_recommender.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mmorikawa.book_recommender.core.database.model.BasicBook
import com.mmorikawa.book_recommender.core.database.model.BookAuthorAssociation
import com.mmorikawa.book_recommender.core.database.model.BookEntity
import com.mmorikawa.book_recommender.core.database.model.BookGenreAssociation
import com.mmorikawa.book_recommender.core.database.model.PopulatedBasicBook
import com.mmorikawa.book_recommender.core.database.model.PopulatedDetailedBook

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = BookEntity::class)
    suspend fun insertOrIgnoreBasicBooks(basicBooks: List<BasicBook>): List<Long>

    @Upsert
    suspend fun upsertBooks(bookEntities: List<BookEntity>)

    @Upsert
    suspend fun upsertBook(bookEntity: BookEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookAuthorAssociations(bookAuthorAssociations: List<BookAuthorAssociation>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookGenreAssociations(bookGenreAssociations: List<BookGenreAssociation>)

    @Transaction
    @Query("SELECT * FROM book WHERE book.id=:bookId")
    suspend fun getDetailedBookById(bookId: Int): PopulatedDetailedBook?

    @Transaction
    @Query("SELECT book.id, book.title, book.cover_url FROM book WHERE book.id=:bookId")
    suspend fun getBasicBookById(bookId: Int): PopulatedBasicBook?

    @Query("SELECT * FROM book WHERE book.id IN (:bookIds)")
    fun getBooksByIds(bookIds: List<Int>): List<BookEntity>

    @Transaction
    @Query("SELECT book.id, book.title, book.cover_url FROM book WHERE book.id IN (:bookIds)")
    suspend fun getBasicBooksByIds(bookIds: List<Int>): Map<@MapColumn(
        columnName = "id",
        tableName = "book"
    ) Int, PopulatedBasicBook?>
}