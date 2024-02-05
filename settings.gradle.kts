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
    ":commons:network",
    ":commons:base",
    ":commons:extensions",
    ":movie",
)
