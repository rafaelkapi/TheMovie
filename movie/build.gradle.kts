plugins {
    id("kotlin-kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    api(project(projects.library.Commons.Modules.base))
    api(project(projects.library.Commons.Modules.network))
    api(project(projects.library.Commons.Modules.extensions))

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.dynamicanimation)
    implementation(libs.google.android.material)
    implementation(libs.androidx.fragment)

    // Kotlin setup library
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk7)

    // Core Ktx
    implementation(libs.androidx.core.ktx)

    // Reactivex
    implementation(libs.rxjava2.rxandroid)
    implementation(libs.rxjava2.rxkotlin)
    implementation(libs.rxjava2.rxjava)

    // Dagger
    implementation(libs.google.dagger.core)
    implementation(libs.google.dagger.support)
    implementation(libs.google.dagger.android)
    kapt(libs.google.dagger.compiler)
    kapt(libs.google.dagger.processor)

    // Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit2.converter.moshi)
    implementation(libs.rxjava2.rxjava.adapter)
    implementation(libs.retrofit2.converter.gson)

    // OkHttp3
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Picasso
    implementation(libs.picasso)

    // UI
    implementation(libs.androidx.swipe.refresh.layout)
    implementation(libs.shimmer)
}