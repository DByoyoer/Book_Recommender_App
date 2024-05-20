package com.mmorikawa.book_recommender.core.data.model

import com.mmorikawa.book_recommender.core.database.model.ReadingListEntity
import com.mmorikawa.book_recommender.core.network.model.NetworkReadingListEntry

fun ReadingListEntity.asNetworkModel() = NetworkReadingListEntry(
    bookId = bookId,
    ranking = ranking,
    dateAdded = dateAdded
)