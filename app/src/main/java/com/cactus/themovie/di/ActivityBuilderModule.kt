package com.cactus.themovie.di

import com.cactus.themovie.di.scopes.ActivityScope
import com.cactus.themovie.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MovieActivity

}
