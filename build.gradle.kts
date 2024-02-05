import core.setupAndroidExtensions
import helpers.applyCustomPluginsByModuleType

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }

    // Plugins Apps
    this.applyCustomPluginsByModuleType(projects.app.MovieApp.projectInfo)

    // Plugins Libraries
    this.applyCustomPluginsByModuleType(projects.library.TheMovie.projectInfo)
    this.applyCustomPluginsByModuleType(projects.library.Commons.projectInfo)

    plugins.withId("com.android.application") {
        this@subprojects.setupAndroidExtensions()
        
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
}

//allprojects {
//    repositories {
//        google()
//        maven("https://jitpack.io")
//    }
//}