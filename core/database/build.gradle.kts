@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.hilt")
    id("book_recommender.android.room")
    // Unsure how to add this to the convention plugin
    id("androidx.room")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    // Unsure how to add this to the convention plugin
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(":core:testing")
}