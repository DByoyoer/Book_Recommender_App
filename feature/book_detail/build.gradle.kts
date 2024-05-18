@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("book_recommender.android.feature")
    id("book_recommender.android.library.compose")
}

android {
    namespace = "com.mmorikawa.book_recommender.feature.book_detail"
}
