package com.mmorikawa.book_recommender.core.data.model

import com.mmorikawa.book_recommender.core.network.model.NetworkRating
import com.mmorikawa.core.model.Rating

fun Rating.asNetworkModel() = NetworkRating(
    bookId = book.id,
    score = score,
    ratingText = ratingText,
    dateCreated = dateCreated,
    dateUpdated = dateUpdated
)
