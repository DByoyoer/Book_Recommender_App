package com.mmorikawa.book_recommender.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mmorikawa.core.model.SimpleBook

data class PopulatedBasicBook(
    @Embedded
    val basicBook: BasicBook,
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
    )
    val genres: List<GenreEntity>,
)

fun PopulatedBasicBook.asExternalModel() = SimpleBook(
    id = basicBook.id,
    title = basicBook.title,
    coverUrl = basicBook.coverUrl,
    authors = authors.map { it.name },
    genres = genres.map { it.name },
)