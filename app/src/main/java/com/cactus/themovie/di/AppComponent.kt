//package com.cactus.themovie.di
//
//import android.content.Context
//import com.cactus.themovie.common.ViewModelFactoryModule
//import com.cactus.network.di.NetworkModule
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjector
//import dagger.android.DaggerApplication
//import dagger.android.support.AndroidSupportInjectionModule
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [
//    AndroidSupportInjectionModule::class,
//    ViewModelFactoryModule::class,
//    ActivityBuilderModule::class,
//    com.cactus.network.di.NetworkModule::class
//    ])
//interface AppComponent : AndroidInjector<DaggerApplication> {
//
//    override fun inject(instance: DaggerApplication)
//
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance applicationContext: Context): AppComponent
//    }
//
//}
//
