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
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    implementation(project(":feature:recommendation"))
    implementation(project(":feature:reading_list"))
    implementation(project(":feature:history"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
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