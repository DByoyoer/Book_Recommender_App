@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("book_recommender.android.library")
    id("book_recommender.android.hilt")
}

android {
    namespace = "com.mmorikawa.book_recommender.core.data"

}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    testImplementation(project(":core:testing"))
}