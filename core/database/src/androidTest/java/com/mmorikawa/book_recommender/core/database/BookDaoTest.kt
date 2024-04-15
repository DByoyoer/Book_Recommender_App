package com.mmorikawa.book_recommender.core.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.mmorikawa.book_recommender.core.database.dao.BookDao
import com.mmorikawa.book_recommender.core.database.model.BasicBook
import com.mmorikawa.book_recommender.core.database.model.BookEntity
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BookDaoTest {
    private lateinit var database: BookRecDatabase
    private lateinit var bookDao: BookDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, BookRecDatabase::class.java).build()
        bookDao = database.bookDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertOrIgnoreBasicBooksTest() = runTest {
        val basicBooks = listOf(
            BasicBook(
                id = 1,
                title = "Book 1",
                coverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg"
            ), BasicBook(
                id = 2,
                title = "Book 2",
                coverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg"
            )
        )
        val insertedIds = bookDao.insertOrIgnoreBasicBooks(basicBooks)
        assertEquals(basicBooks.size, insertedIds.size)
    }

    @Test
    fun upsertBookTest() {
        val bookEntity = BookEntity(
            id = 1,
            title = "Book 1",
            pages = 100,
            coverUrl = "https://images.gr-assets.com/books/1447303603m/2767052.jpg",
            description = "Description",
            isbn = "123456789",
            isbn13 = "1234567891223",
            langCode = "Eng",
            originalPublicationYear = 2020
        )
        runBlocking { bookDao.upsertBook(bookEntity) }
        val retrievedBook = runBlocking { bookDao.getDetailedBookById(bookEntity.id) }
        assertEquals(bookEntity.id, retrievedBook.bookEntity.id)
        assertEquals(bookEntity.title, retrievedBook.bookEntity.title)
    }

    // TODO: Add more tests
}