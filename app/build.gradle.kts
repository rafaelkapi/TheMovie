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
    api(project(projects.library.TheMovie.Modules.movie))

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk7)
    implementation(libs.kotlin.reflect)

    // AndroidUi
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.android.material)
    implementation(libs.androidx.dynamicanimation)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.reactivestreams)

    // Dagger
    implementation(libs.google.dagger.core)
    implementation(libs.google.dagger.support)
    implementation(libs.google.dagger.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.google.dagger.compiler)
    kapt(libs.google.dagger.processor)
    kapt(libs.androidx.lifecycle.compiler)

    // Reactivex
    implementation(libs.rxjava2.rxandroid)
    implementation(libs.rxjava2.rxkotlin)
    implementation(libs.rxjava2.rxjava)

    // Ktx Extension
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Moshi
    implementation(libs.moshi.kotlin)

    // OkHttp3
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    // Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit2.converter.moshi)
    implementation(libs.rxjava2.rxjava.adapter)
    implementation(libs.retrofit2.converter.gson)
}