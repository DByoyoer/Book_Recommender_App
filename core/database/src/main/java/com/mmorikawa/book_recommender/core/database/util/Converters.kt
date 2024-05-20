package com.mmorikawa.book_recommender.core.database.util

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {
    @TypeConverter
    fun stringToInstant(value: String?): Instant? =
        value?.let(Instant::parse)

    @TypeConverter
    fun instantToString(instant: Instant?): String? =
        instant?.toString()
}