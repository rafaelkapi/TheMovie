/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie.di.activitylifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import javax.inject.Inject
import javax.inject.Provider

class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {

    private val activityLifeCycleItems: List<ActivityLifecycleListener>

    @Inject constructor(
        listeners: Map<Class<out ActivityLifecycleListener>, @JvmSuppressWildcards Provider<ActivityLifecycleListener>>
    ) : this(listeners.map { it.value.get() })

    @VisibleForTesting
    constructor(listeners: List<ActivityLifecycleListener>) {
        this.activityLifeCycleItems = listeners
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityLifeCycleItems.forEach { it.onActivityCreated(activity.localClassName) }
    }

    override fun onActivityStarted(activity: Activity) {
        activityLifeCycleItems.forEach { it.onActivityStarted(activity.localClassName) }
    }

    override fun onActivityResumed(activity: Activity) {
        activityLifeCycleItems.forEach { it.onActivityResumed(activity.localClassName) }
    }

    override fun onActivityPaused(activity: Activity) {
        activityLifeCycleItems.forEach { it.onActivityPaused(activity.localClassName) }
    }

    override fun onActivityStopped(activity: Activity) {
        activityLifeCycleItems.forEach { it.onActivityStopped(activity.localClassName) }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        activityLifeCycleItems.forEach { it.onActivitySaveInstanceState(activity.localClassName) }
    }

    override fun onActivityDestroyed(activity: Activity) {
        activityLifeCycleItems.forEach { it.onActivityDestroyed(activity.localClassName) }
    }
}
