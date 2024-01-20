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
    ":commons",
    ":movie"
)

