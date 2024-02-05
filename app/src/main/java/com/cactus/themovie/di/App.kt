//package com.cactus.themovie.di
//
//import dagger.android.AndroidInjector
//import dagger.android.DaggerApplication
//
//class App : DaggerApplication() {
//
//    lateinit var appComponent: AppComponent
//
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
//
//        appComponent = DaggerAppComponent.factory()
//            .create(this)
//
//        appComponent.inject(this)
//
//        return appComponent
//    }
//}