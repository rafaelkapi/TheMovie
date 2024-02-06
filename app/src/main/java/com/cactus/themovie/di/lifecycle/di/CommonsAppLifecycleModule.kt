/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie.di.lifecycle.di

import androidx.lifecycle.LifecycleObserver
import com.cactus.themovie.di.lifecycle.ApplicationLifecycleObserver
import com.cactus.themovie.di.lifecycle.ApplicationLifecycleObserverImpl
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Module
abstract class CommonsAppLifecycleModule {
    @Binds
    abstract fun providesLifecycleInit(impl: ApplicationLifecycleObserverImpl): ApplicationLifecycleObserver
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class LifecycleObserverKey(val value: KClass<out LifecycleObserver>)
