plugins {
    id("book_recommender.android.library")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.testing"
}

dependencies {
    implementation(project(":core:model"))
}