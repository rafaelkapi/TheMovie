/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {
    @Override
    open fun onCreate() {
        // To be overridden when necessary
    }

    @Override
    open fun onStart() {
        // To be overridden when necessary
    }

    @Override
    open fun onResume() {
        // To be overridden when necessary
    }

    @Override
    open fun onPause() {
        // To be overridden when necessary
    }

    @Override
    open fun onStop() {
        // To be overridden when necessary
    }

    @Override
    open fun onDestroy() {
        // To be overridden when necessary
    }

    protected val compositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plus(d: Disposable) {
        this.add(d)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
