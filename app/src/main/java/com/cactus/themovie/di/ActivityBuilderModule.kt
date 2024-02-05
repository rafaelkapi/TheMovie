//package com.cactus.themovie.di
//
//import com.cactus.themovie.MainActivity
//import com.cactus.themovie.di.scopes.ActivityScope
//import com.cactus.movie.moviedetails.di.DetailsModuleBuilder
//import dagger.Module
//import dagger.android.ContributesAndroidInjector
//
//@Module
//abstract class ActivityBuilderModule {
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = [com.cactus.movie.moviedetails.di.DetailsModuleBuilder::class])
//    abstract fun bindMainActivity(): MainActivity
//
//}
