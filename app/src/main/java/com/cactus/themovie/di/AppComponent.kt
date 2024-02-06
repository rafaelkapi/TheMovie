package com.cactus.themovie.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cactus.commons.base.ViewModelModule
import com.cactus.movie.moviedetails.di.DetailsModuleBuilder
import com.cactus.network.CommonsSchedulersModule
import com.cactus.network.di.CommonsContextModule
import com.cactus.network.di.CommonsNetworkBuilderModule
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
    ViewModelModule::class,
    MovieActivityBuilderModule::class,
    CommonsNetworkBuilderModule::class,
    CommonsSchedulersModule::class,
    DetailsModuleBuilder::class,
    CommonsContextModule::class
    ])
interface AppComponent : AndroidInjector<DaggerApplication> {

//    fun getViewModelMap(): Map<Class<*>, ViewModel>

    fun inject(application: CustomApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

