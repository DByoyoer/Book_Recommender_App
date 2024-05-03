@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.hilt")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.common"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
}