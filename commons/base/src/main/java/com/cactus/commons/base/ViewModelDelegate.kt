/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ViewModelDelegate<T : BaseViewModel>(
    private val clazz: KClass<T>,
    private val activity: FragmentActivity,
    private val vmFactory: () -> DaggerViewModelFactory
) : ReadWriteProperty<FragmentActivity, T> {

    override fun getValue(thisRef: FragmentActivity, property: KProperty<*>): T {
        return ViewModelProvider(activity, vmFactory.invoke()).get(clazz.java).apply {
            thisRef.lifecycle.addObserver(this)
        }
    }

    override fun setValue(thisRef: FragmentActivity, property: KProperty<*>, value: T) {}
}
