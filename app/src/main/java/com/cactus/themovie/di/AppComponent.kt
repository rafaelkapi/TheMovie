package com.cactus.themovie.di

import android.app.Application
import com.cactus.commons.base.ViewModelModule
import com.cactus.network.CommonsSchedulersModule
import com.cactus.network.di.NetworkModule
import com.cactus.themovie.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MovieActivityBuilderModule::class,
    NetworkModule::class,
    CommonsSchedulersModule::class,
    ViewModelModule::class,
    ])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CustomApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

