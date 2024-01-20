plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(buildSrcLibs.android.gradle.plugin)
    implementation(buildSrcLibs.kotlin.gradle.plugin)
    implementation(buildSrcLibs.kotlin.gradle.api.plugin)
    implementation(buildSrcLibs.kotlin.dsl.plugin)
}