/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.interceptor

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class CommonsSubscriptionKeyInterceptor @Inject constructor(
//    private val environment: SelectedEnvironment
) : Interceptor {

    companion object {
        private const val CONST_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newBuilder = request.newBuilder().apply {
            header(CONST_SUBSCRIPTION_KEY, "todo")
        }

        return chain.proceed(newBuilder.build())
    }
}
