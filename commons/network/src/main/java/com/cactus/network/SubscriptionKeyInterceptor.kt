package com.cactus.network

import com.cactus.network.di.PrivateKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SubscriptionKeyInterceptor @Inject constructor(
    @PrivateKey private val privateKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
//        val builder = request.url.newBuilder()

//        builder.addQueryParameter(API_PARAMETER, privateKey)
//
//        request = request.newBuilder().url(builder.build()).build()
        return chain.proceed(request)
    }

    companion object {
        const val API_PARAMETER = "api_key"
    }
}
