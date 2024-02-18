plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.library.compose")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.testing"
}

dependencies {
    implementation(project(":core:model"))
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.junit)
    api(libs.androidx.test.rules)

}