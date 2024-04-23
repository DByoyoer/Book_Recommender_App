package com.mmorikawa.book_recommender.core.data.repository

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
import javax.inject.Inject

class OfflineFirstBookRepository @Inject constructor(
    private val networkDataSource: BookRecNetworkDataSource,
    private val bookDao: BookDao,
    private val authorDao: AuthorDao,
    private val genreDao: GenreDao
) : BookRepository {
    override suspend fun getSimpleBookById(id: Int): SimpleBook {
        var book = bookDao.getBasicBookById(id)
        if (book == null) {
            val networkBook: NetworkBook = networkDataSource.getBook(id)
            bookDao.upsertBook(networkBook.asEntity())
            authorDao.insertOrIgnoreAuthors(networkBook.authors())
            genreDao.insertOrIgnoreGenres(networkBook.genres())
            bookDao.insertBookAuthorAssociations(networkBook.authorAssociations())
            bookDao.insertBookGenreAssociations(networkBook.genreAssociations())
            book = networkBook.toPopulatedSimpleBook()
        }

        return book.asExternalModel()
    }

    override suspend fun getSimpleBooksByIds(ids: List<Int>): List<SimpleBook> {
        val books = bookDao.getBasicBooksByIds(ids).toMutableMap()
        if (books.size < ids.size) {
            for (id in ids) {
                if (books[id] == null) {
                    val networkBook: NetworkBook = networkDataSource.getBook(id)
                    bookDao.upsertBook(networkBook.asEntity())
                    authorDao.insertOrIgnoreAuthors(networkBook.authors())
                    genreDao.insertOrIgnoreGenres(networkBook.genres())
                    bookDao.insertBookAuthorAssociations(networkBook.authorAssociations())
                    bookDao.insertBookGenreAssociations(networkBook.genreAssociations())
                    books[id] = networkBook.toPopulatedSimpleBook()
                }
            }
        }

        return books.mapValues { it.value.asExternalModel() }.values.toList()
    }

    override suspend fun getDetailedBookById(id: Int): DetailedBook {
        var book = bookDao.getDetailedBookById(id)
        if (book == null || book.bookEntity.description == "") {
            val networkBook = networkDataSource.getBook(id)
            bookDao.upsertBook(networkBook.asEntity())
            authorDao.insertOrIgnoreAuthors(networkBook.authors())
            genreDao.insertOrIgnoreGenres(networkBook.genres())
            bookDao.insertBookAuthorAssociations(networkBook.authorAssociations())
            bookDao.insertBookGenreAssociations(networkBook.genreAssociations())
            book = networkBook.toPopulatedDetailedBook()
        }
        
        return book.asExternalModel()
    }
}