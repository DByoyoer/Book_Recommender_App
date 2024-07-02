import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("book_recommender.android.application")
    id("book_recommender.android.application.compose")
    id("book_recommender.android.hilt")
}

android {
    namespace = "com.mmorikawa.book_recommender"

    defaultConfig {
        applicationId = "com.mmorikawa.book_recommender"
        versionCode = 1
        versionName = "0.1.0"
        archivesName = "book-rec-$versionName"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":feature:book_detail"))
    implementation(project(":feature:recommendation"))
    implementation(project(":feature:reading_list"))
    implementation(project(":feature:history"))
    implementation(project(":feature:search"))



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    androidTestImplementation(project(":core:testing"))
}

kapt {
    correctErrorTypes = true
}