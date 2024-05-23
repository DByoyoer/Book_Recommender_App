package com.mmorikawa.book_recommender.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {

    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Create new table with score as float
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS `rating_new` " +
                        "(`book_id` INTEGER NOT NULL, `score` REAL NOT NULL, " +
                        "`rating_text` TEXT NOT NULL, `date_created` TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        " `date_updated` TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        "PRIMARY KEY(`book_id`), " +
                        "FOREIGN KEY(`book_id`) REFERENCES `book`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )"
            )

            // Copy data into new table
            db.execSQL(
                "INSERT INTO rating_new (book_id, score, rating_text, date_created, date_updated)" +
                        "SELECT book_id, score, rating_text, date_created, date_updated FROM rating"
            )

            // Delete old table
            db.execSQL("DROP TABLE rating")

            // Rename old table
            db.execSQL("ALTER TABLE rating_new RENAME TO rating")
        }
    }
}