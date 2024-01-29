buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.androidx.navigation.safe.args.plugin)
        classpath(libs.google.services.plugin)
        classpath(libs.google.protobuf.plugin)
        classpath(libs.google.dagger.hilt.android.plugin)
        classpath(libs.kotlin.gradle.plugin)
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}