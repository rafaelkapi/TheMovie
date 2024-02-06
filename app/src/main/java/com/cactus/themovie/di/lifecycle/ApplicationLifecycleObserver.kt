/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie.di.lifecycle

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import javax.inject.Inject
import javax.inject.Provider

interface ApplicationLifecycleObserver {
    fun addObservers()
}

class ApplicationLifecycleObserverImpl @Inject constructor(
    private val lifeCycleMap: Map<Class<out LifecycleObserver>, @JvmSuppressWildcards Provider<LifecycleObserver>>
) : ApplicationLifecycleObserver {

    override fun addObservers() {
        lifeCycleMap.forEach {
            ProcessLifecycleOwner.get().lifecycle.addObserver(it.value.get())
        }
    }
}
