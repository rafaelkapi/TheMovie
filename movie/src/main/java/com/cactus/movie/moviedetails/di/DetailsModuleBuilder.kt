package com.cactus.movie.moviedetails.di

import androidx.lifecycle.ViewModel
import com.cactus.commons.base.ViewModelKey
import com.cactus.commons.base.ViewModelModule
import com.cactus.movie.moviedetails.data.DetailsRepository
import com.cactus.movie.moviedetails.data.DetailsRepositoryImpl
import com.cactus.movie.moviedetails.data.DetailsService
import com.cactus.movie.moviedetails.di.qualifiers.FragmentScope
import com.cactus.movie.moviedetails.domain.DetailsInteractor
import com.cactus.movie.moviedetails.domain.DetailsInteractorImpl
import com.cactus.movie.moviedetails.presentation.DetailsFragment
import com.cactus.movie.moviedetails.presentation.DetailsViewModel
import com.cactus.network.di.CommonsNetworkBuilderModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [DetailsModule::class,])
abstract class DetailsModuleBuilder {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindsDetailsFragment(): DetailsFragment
}

@Module(includes = [CommonsNetworkBuilderModule::class])
abstract class DetailsModule {

    @Binds
    abstract fun bindDetailsRepository(impl: DetailsRepositoryImpl): DetailsRepository

    @Binds
    abstract fun bindDetailsInteractor(impl: DetailsInteractorImpl): DetailsInteractor

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): DetailsService =
            retrofit.create(DetailsService::class.java)
    }
}




