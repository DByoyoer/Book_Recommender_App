package com.mmorikawa.book_recommender.core.data.model

import com.mmorikawa.book_recommender.core.database.model.AuthorEntity
import com.mmorikawa.book_recommender.core.database.model.BookAuthorAssociation
import com.mmorikawa.book_recommender.core.database.model.BookEntity
import com.mmorikawa.book_recommender.core.database.model.BookGenreAssociation
import com.mmorikawa.book_recommender.core.database.model.GenreEntity
import com.mmorikawa.book_recommender.core.network.model.NetworkBook
import com.mmorikawa.core.model.SimpleBook

fun NetworkBook.asEntity() = BookEntity(
    id = id,
    title = title,
    description = description,
    isbn = isbn,
    isbn13 = isbn13,
    pages = pages,
    originalPublicationYear = originalPublicationYear,
    langCode = langCode,
    coverUrl = coverUrl
)

fun NetworkBook.toSimpleBook() = SimpleBook(
    id = id,
    title = title,
    coverUrl = coverUrl,
    authors = authors.map { it.name },
    genres = genres.map { it.name }
)

fun NetworkBook.authors(): List<AuthorEntity> =
    authors.map { author -> AuthorEntity(id = author.id, name = author.name) }

fun NetworkBook.authorAssociations(): List<BookAuthorAssociation> =
    authors.map { author -> BookAuthorAssociation(bookId = id, authorId = author.id) }

fun NetworkBook.genres(): List<GenreEntity> =
    genres.map { genre -> GenreEntity(id = genre.id, name = genre.name) }

fun NetworkBook.genreAssociations(): List<BookGenreAssociation> =
    genres.map { genre -> BookGenreAssociation(bookId = id, genreId = genre.id) }