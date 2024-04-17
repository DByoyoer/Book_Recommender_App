package com.mmorikawa.book_recommender.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mmorikawa.core.model.BookDetailed

data class PopulatedDetailedBook(
    @Embedded
    val bookEntity: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = BookAuthorAssociation::class,
            parentColumn = "book_id",
            entityColumn = "author_id"
        ),
        entity = AuthorEntity::class
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = BookGenreAssociation::class,
            parentColumn = "book_id",
            entityColumn = "genre_id"
        ),
        entity = GenreEntity::class,
    )
    val genres: List<GenreEntity>

)

fun PopulatedDetailedBook.asExternalModel() = BookDetailed(
    id = bookEntity.id,
    title = bookEntity.title,
    description = bookEntity.description,
    isbn = bookEntity.isbn,
    isbn13 = bookEntity.isbn13,
    coverUrl = bookEntity.coverUrl,
    langCode = bookEntity.langCode,
    originalPublicationYear = bookEntity.originalPublicationYear,
    pages = bookEntity.pages,
    authors = authors.map { it.name },
    genres = genres.map { it.name },
)
