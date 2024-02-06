/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.interceptor

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class CommonsBearerTokenInterceptor @Inject constructor(
) : Interceptor {

    private companion object {
        const val CONST_AUTHORIZATION = "Authorization"
        const val CONST_BEARER = "Bearer "
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

//        if (environment.isTrustedDomain(request.url().toString()).not())
//            return chain.proceed(request.newBuilder().build())
//
//        val newBuilder = request.newBuilder()
//        if (bearerSession.getAuthBearer().isNotEmpty()) {
//            newBuilder.apply {
//                val authorization: String = CONST_BEARER + bearerSession.getAuthBearer()
//                header(CONST_AUTHORIZATION, authorization)
//            }
//        }

//        return chain.proceed(newBuilder.build())
        return chain.proceed(request)
    }
}
