/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.cactus.themovie.di.DaggerApplicationComponent

class CustomApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)


        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
    }
}
