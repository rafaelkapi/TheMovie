/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie

import com.cactus.themovie.di.AppComponent
import com.cactus.themovie.di.DaggerAppComponent
//import com.cactus.themovie.di.lifecycle.ApplicationLifecycleObserver
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class CustomApplication : DaggerApplication() {
    private lateinit var appComponent: AppComponent

//    @Inject
//    lateinit var applicationLifecycleObserver: ApplicationLifecycleObserver


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
//        applicationLifecycleObserver.addObservers()

    }
}
