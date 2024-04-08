pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Book Recommender"
include(":app")
include(":core:designsystem")
include(":core:ui")
include(":core:model")
include(":feature:home")
include(":feature:reading_list")
include(":feature:history")
include(":feature:settings")
include(":feature:recommendation")
include(":core:testing")
include(":core:data")
include(":core:database")
include(":core:network")
