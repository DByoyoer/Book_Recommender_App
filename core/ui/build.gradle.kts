plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.library.compose")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)

    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.core.ktx)
    
}