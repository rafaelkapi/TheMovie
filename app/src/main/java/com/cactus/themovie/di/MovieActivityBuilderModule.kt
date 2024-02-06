package com.cactus.themovie.di

import com.cactus.themovie.di.scopes.ActivityScope
import com.cactus.themovie.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMovieActivity(): MovieActivity

}
