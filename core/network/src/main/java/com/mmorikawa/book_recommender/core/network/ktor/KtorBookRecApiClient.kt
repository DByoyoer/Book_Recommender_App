package com.mmorikawa.book_recommender.core.network.ktor

import com.mmorikawa.book_recommender.core.network.model.NetworkAuthor
import com.mmorikawa.book_recommender.core.network.model.NetworkBook
import com.mmorikawa.book_recommender.core.network.model.NetworkRating
import com.mmorikawa.book_recommender.core.network.model.NetworkReadingListEntry
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorBookRecApiClient {
    private val httpClient = HttpClient(OkHttp) {
        engine {
            config {
                // Empty
            }
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        defaultRequest {
            url("10.0.2.2:8000/")
        }
    }

    suspend fun getBook(bookId: Int): NetworkBook {
        return httpClient.get("books/$bookId").body()
    }

    suspend fun getBookRecs(): List<NetworkBook> {
        // TODO: Figure out account stuff for user id
        val userId = 1234
        return httpClient.get("users/$userId/recs").body()
    }

    suspend fun getReadingList(): List<NetworkReadingListEntry> {
        val userId = 53426
        return httpClient.get("users/$userId/reading_list").body()
    }

    suspend fun getRatings() {
        val userId = 53426
        return httpClient.get("users/$userId/ratings").body()
    }

    suspend fun getAuthor(authorId: Int): NetworkAuthor {
        return httpClient.get("authors/$authorId").body()
    }

    suspend fun postRating(rating: NetworkRating) {
        val userId = 53426
        val response: HttpResponse = httpClient.post("users/$userId/ratings") {
            contentType(ContentType.Application.Json)
            setBody(rating)
        }
    }

    suspend fun postReadingListEntry(readingListEntry: NetworkReadingListEntry) {
        val userId = 53426
        val response: HttpResponse = httpClient.post("users/$userId/reading_list") {
            contentType(ContentType.Application.Json)
            setBody(readingListEntry)
        }
    }

    suspend fun putRating(rating: NetworkRating) {
        val userId = 53426
        val response: HttpResponse = httpClient.put("users/$userId/ratings/${rating.bookId}") {
            contentType(ContentType.Application.Json)
            setBody(rating)
        }
    }

    suspend fun putReadingListEntry(readingListEntry: NetworkReadingListEntry) {
        val userId = 53426
        val response: HttpResponse =
            httpClient.put("users/$userId/reading_list/${readingListEntry.bookId}") {
                contentType(ContentType.Application.Json)
                setBody(readingListEntry)
            }
    }
}