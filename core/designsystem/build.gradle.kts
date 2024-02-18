plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.library.compose")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.designsystem"


    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.runtime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
}