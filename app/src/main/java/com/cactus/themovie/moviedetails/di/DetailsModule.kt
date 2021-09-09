package com.cactus.themovie.moviedetails.di

import androidx.lifecycle.ViewModel
import com.cactus.marvelcomics.common.ViewModelKey
import com.cactus.themovie.di.scopes.FragmentScope
import com.cactus.themovie.moviedetails.data.DetailsRepository
import com.cactus.themovie.moviedetails.data.DetailsRepositoryImp
import com.cactus.themovie.moviedetails.data.DetailsService
import com.cactus.themovie.moviedetails.view.DetailsFragment
import com.cactus.themovie.moviedetails.viewmodel.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class DetailsModuleBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailsDataModule::class, DetailsModule::class])
    abstract fun bindsDetailsFragment(): DetailsFragment
}

@Module
abstract class DetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}



@Module
abstract class DetailsDataModule {

    @Binds
    abstract fun provideLocalDataSource(repository: DetailsRepositoryImp): DetailsRepository


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): DetailsService =
            retrofit.create(DetailsService::class.java)
    }

}
