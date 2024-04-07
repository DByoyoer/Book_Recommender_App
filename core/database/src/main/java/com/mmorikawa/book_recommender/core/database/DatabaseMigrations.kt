package com.mmorikawa.book_recommender.core.database

import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec

object DatabaseMigrations {
    @RenameColumn(
        tableName = "ratings",
        fromColumnName = "rating",
        toColumnName = "score"
    )
    class Schema1to2 : AutoMigrationSpec

    @RenameTable(
        fromTableName = "ratings",
        toTableName = "rating"
    )
    @RenameTable(
        fromTableName = "books",
        toTableName = "book"
    )
    @RenameTable(
        fromTableName = "authors",
        toTableName = "author"
    )
    @RenameTable(
        fromTableName = "book_authors",
        toTableName = "book_author"
    )
    class Schema2to3 : AutoMigrationSpec

}