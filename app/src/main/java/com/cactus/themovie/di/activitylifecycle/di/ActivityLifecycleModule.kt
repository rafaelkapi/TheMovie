/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie.di.activitylifecycle.di

import android.app.Application
import com.cactus.themovie.di.activitylifecycle.ActivityLifecycleCallbacksImpl
import com.cactus.themovie.di.activitylifecycle.ActivityLifecycleListener
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Module
abstract class ActivityLifecycleModule {

    @Binds
    abstract fun providesLifecycleInit(impl: ActivityLifecycleCallbacksImpl): Application.ActivityLifecycleCallbacks
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ActivityLifecycleKey(val value: KClass<out ActivityLifecycleListener>)
