/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.themovie.di.activitylifecycle

interface ActivityLifecycleListener {
    fun onActivityCreated(activityLocalClassName: String) {}
    fun onActivityStarted(activityLocalClassName: String) {}
    fun onActivityResumed(activityLocalClassName: String) {}
    fun onActivityPaused(activityLocalClassName: String) {}
    fun onActivityStopped(activityLocalClassName: String) {}
    fun onActivitySaveInstanceState(activityLocalClassName: String) {}
    fun onActivityDestroyed(activityLocalClassName: String) {}
}
