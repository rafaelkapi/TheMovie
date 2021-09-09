package com.cactus.themovie.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor


