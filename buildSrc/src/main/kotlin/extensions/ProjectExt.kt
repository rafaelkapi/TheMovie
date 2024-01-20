package extensions

import org.gradle.api.Project

fun Project.isAndroidModule(): Boolean {
    val isAndroidLibrary = this.plugins.hasPlugin("com.android.library")
    val isAndroidApp = this.plugins.hasPlugin("com.android.application")
    return isAndroidLibrary || isAndroidApp
}

fun Project.isJavaModule(): Boolean {
    val isJavaLibrary = this.plugins.hasPlugin("java-library")
    return isJavaLibrary
}