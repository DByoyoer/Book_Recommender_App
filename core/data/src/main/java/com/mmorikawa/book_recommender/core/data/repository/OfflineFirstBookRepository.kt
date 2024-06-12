package com.mmorikawa.book_recommender.core.data.repository

import android.util.Log
import com.mmorikawa.book_recommender.core.common.dispatchers.BookRecDispatchers
import com.mmorikawa.book_recommender.core.common.dispatchers.Dispatcher
import com.mmorikawa.book_recommender.core.data.model.asEntity
import com.mmorikawa.book_recommender.core.data.model.authorAssociations
import com.mmorikawa.book_recommender.core.data.model.authors
import com.mmorikawa.book_recommender.core.data.model.genreAssociations
import com.mmorikawa.book_recommender.core.data.model.genres
import com.mmorikawa.book_recommender.core.data.model.toPopulatedDetailedBook
import com.mmorikawa.book_recommender.core.data.model.toPopulatedSimpleBook
import com.mmorikawa.book_recommender.core.database.dao.AuthorDao
import com.mmorikawa.book_recommender.core.database.dao.BookDao
import com.mmorikawa.book_recommender.core.database.dao.GenreDao
import com.mmorikawa.book_recommender.core.database.model.asExternalModel
import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
import com.mmorikawa.book_recommender.core.network.model.NetworkBook
import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.model.SimpleBook
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstBookRepository @Inject constructor(
    private val networkDataSource: BookRecNetworkDataSource,
    private val bookDao: BookDao,
    private val authorDao: AuthorDao,
    private val genreDao: GenreDao,
    @Dispatcher(BookRecDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : BookRepository {
    override fun getSimpleBookStream(id: Int): Flow<SimpleBook> {
        TODO("Not yet implemented")
    }

    override fun getDetailedBookStream(id: Int): Flow<DetailedBook> =
        bookDao.observeDetailedBookById(id).map {
            if (it == null || it.bookEntity.description == "") {
                val networkBook = networkDataSource.getBook(id)
                insertNetworkBook(networkBook)
                networkBook.toPopulatedDetailedBook().asExternalModel()
            } else {
                it.asExternalModel()
            }
        }

    override fun getDetailedBooksStream(ids: List<Int>): Flow<List<SimpleBook>> {
        TODO("Not yet implemented")
    }

    override fun getSimpleBooksStream(ids: List<Int>): Flow<List<SimpleBook>> =
        bookDao.observeBasicBooksByIds(ids).map { bookMap ->
            if (bookMap.size != ids.size) {
                for (id in ids) {
                    if (bookMap[id] == null) {
                        val networkBook = networkDataSource.getBook(id)
                        insertNetworkBook(networkBook)
                    }
                }
            }
            bookMap.values.map { it.asExternalModel() }
        }

    override suspend fun getSimpleBookById(id: Int): SimpleBook {

        return withContext(ioDispatcher) {
            var book = bookDao.getBasicBookById(id)
            if (book == null) {
                val networkBook: NetworkBook = networkDataSource.getBook(id)
                insertNetworkBook(networkBook)
                book = networkBook.toPopulatedSimpleBook()
            }
            book.asExternalModel()
        }
    }

    override suspend fun getSimpleBooksByIds(ids: List<Int>): List<SimpleBook> =
        withContext(ioDispatcher) {
            val books = bookDao.getBasicBooksByIds(ids).toMutableMap()
            Log.d("Blah", "Not flow: $books")
            if (books.size != ids.size) {
                for (id in ids) {
                    if (books[id] == null) {
                        val networkBook: NetworkBook = networkDataSource.getBook(id)
                        insertNetworkBook(networkBook)
                        books[id] = networkBook.toPopulatedSimpleBook()
                    }
                }
            }
            // TODO: Find way to remove non-null assertion
            books.mapValues { it.value!!.asExternalModel() }.values.toList()
        }

    override suspend fun getDetailedBookById(id: Int): DetailedBook {
        var book = bookDao.getDetailedBookById(id)
        if (book == null || book.bookEntity.description == "") {
            val networkBook = networkDataSource.getBook(id)
            insertNetworkBook(networkBook)
            book = networkBook.toPopulatedDetailedBook()
        }

        return book.asExternalModel()
    }

    override fun getRecommendations(): Flow<List<SimpleBook>> = flow {
        val books = withContext(ioDispatcher) {
            val books = networkDataSource.getBookRecs()
            books.map { insertNetworkBook(it) }
            books.map { it.toPopulatedSimpleBook().asExternalModel() }
        }
        emit(books)
    }

    override fun searchBooks(query: String): Flow<List<SimpleBook>> = flow {
        val books = withContext(ioDispatcher) {
            val books = networkDataSource.searchBooks(query)
            Log.d("blah", books.toString())
            books.map { insertNetworkBook(it) }
            books.map { it.toPopulatedSimpleBook().asExternalModel() }
        }
        emit(books)
    }

    private suspend fun insertNetworkBook(networkBook: NetworkBook) = withContext(ioDispatcher) {
        Log.d("BOOK_REPOSITORY", "INSERTING $NetworkBook")
        bookDao.upsertBook(networkBook.asEntity())
        authorDao.insertOrIgnoreAuthors(networkBook.authors())
        genreDao.insertOrIgnoreGenres(networkBook.genres())
        bookDao.insertBookAuthorAssociations(networkBook.authorAssociations())
        bookDao.insertBookGenreAssociations(networkBook.genreAssociations())
    }
}