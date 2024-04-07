package com.mmorikawa.book_recommender.core.database

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

object DatabaseMigrations {
    @RenameColumn(
        tableName = "ratings",
        fromColumnName = "rating",
        toColumnName = "score"
    )
    class Schema1to2 : AutoMigrationSpec
}