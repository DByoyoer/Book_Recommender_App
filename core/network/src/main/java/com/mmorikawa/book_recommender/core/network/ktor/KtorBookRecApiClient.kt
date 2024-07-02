package com.mmorikawa.book_recommender.core.network.ktor

import com.mmorikawa.book_recommender.core.network.BookRecNetworkDataSource
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
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorBookRecApiClient @Inject constructor() : BookRecNetworkDataSource {
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
            url("https://bookrec.azurewebsites.net/")
        }
    }

    override suspend fun getBook(bookId: Int): NetworkBook {
        return httpClient.get("books/$bookId").body()
    }

    override suspend fun getBookRecs(): List<NetworkBook> {
        // TODO: Figure out account stuff for user id
        val userId = 53426
        return httpClient.get("users/$userId/recs").body()
    }

    override suspend fun deleteRating(bookId: Int) {
        val userId = 53426
        httpClient.delete("users/$userId/ratings/$bookId")
    }

    override suspend fun getReadingList(): List<NetworkReadingListEntry> {
        val userId = 53426
        return httpClient.get("users/$userId/reading_list").body()
    }

    override suspend fun getRatings(): List<NetworkRating> {
        val userId = 53426
        return httpClient.get("users/$userId/ratings").body()
    }

    override suspend fun getAuthor(authorId: Int): NetworkAuthor {
        return httpClient.get("authors/$authorId").body()
    }

    override suspend fun createRating(rating: NetworkRating) {
        val userId = 53426
        val response: HttpResponse = httpClient.post("users/$userId/ratings") {
            contentType(ContentType.Application.Json)
            setBody(rating)
        }
    }

    override suspend fun createReadingListEntry(readingListEntry: NetworkReadingListEntry) {
        val userId = 53426
        val response: HttpResponse = httpClient.post("users/$userId/reading_list") {
            contentType(ContentType.Application.Json)
            setBody(readingListEntry)
        }
    }

    override suspend fun updateRating(rating: NetworkRating) {
        val userId = 53426
        val response: HttpResponse = httpClient.put("users/$userId/ratings/${rating.bookId}") {
            contentType(ContentType.Application.Json)
            setBody(rating)
        }
    }

    override suspend fun updateReadingListEntry(readingListEntry: NetworkReadingListEntry) {
        val userId = 53426
        val response: HttpResponse =
            httpClient.put("users/$userId/reading_list/${readingListEntry.bookId}") {
                contentType(ContentType.Application.Json)
                setBody(readingListEntry)
            }
    }

    override suspend fun getTopNBooks(n: Int): List<NetworkBook> {
        return httpClient.get("books/top_books") {
            url {
                parameters.append("n", "$n")
            }
        }.body()
    }

    override suspend fun searchBooks(query: String): List<NetworkBook> {
        return httpClient.get("books") {
            url {
                parameters.append("q", query)
            }
        }.body()
    }
}