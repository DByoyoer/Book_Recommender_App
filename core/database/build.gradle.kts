@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.hilt")
    id("book_recommender.android.room")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    androidTestImplementation(":core:testing")
}