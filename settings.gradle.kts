rootProject.name = "TheMovie"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
include(
    ":movie",
    ":app",
    ":app:themovie",
    ":commons",
    ":movie",

)

