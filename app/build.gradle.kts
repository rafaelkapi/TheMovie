import helpers.applyCustomPluginsByModuleType
import projects.app.MovieApp


subprojects {
    this.applyCustomPluginsByModuleType(MovieApp.projectInfo)
    configure<com.android.build.gradle.BaseExtension> {
        signingConfigs {
            create("release") {
                isV2SigningEnabled = true
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "11"
        }
        defaultConfig {
            manifestPlaceholders["scheme_app"] = "hubxp"
            vectorDrawables.useSupportLibrary = true
            versionCode = 144
            versionName = "8.13.0"
        }
        buildTypes {
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
                resValue("string", "app_name", "Hub XP")
            }

            getByName("debug") {
                if (project.project.path.trim().endsWith("app")) {
                    applicationIdSuffix = ".hml"
                }
                resValue("string", "app_name", "Hub XP - HML")
            }
        }
    }
}