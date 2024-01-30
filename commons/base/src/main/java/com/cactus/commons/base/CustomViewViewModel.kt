/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : BaseViewModel> customViewViewModel(
    owner: FragmentActivity,
    factory: DaggerViewModelFactory
): VM {
    return ViewModelProvider(owner, factory).get(VM::class.java).apply {
        owner.lifecycle.addObserver(this)
    }
}
