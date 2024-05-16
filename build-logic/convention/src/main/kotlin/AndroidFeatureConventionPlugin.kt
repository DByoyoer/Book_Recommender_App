import com.android.build.gradle.LibraryExtension
import com.mmorikawa.book_recommender.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("book_recommender.android.library")
                apply("book_recommender.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                //  Nothing yet
            }
            dependencies {
                // Core modules
                add("implementation", project(":core:data"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:testing"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary("coil.kt.compose").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("kotlinx.datetime").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

            }
        }
    }
}