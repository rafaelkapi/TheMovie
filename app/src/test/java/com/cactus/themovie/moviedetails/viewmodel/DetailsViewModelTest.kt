package com.cactus.themovie.moviedetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cactus.themovie.moviedetails.data.DetailsRepository
import com.cactus.themovie.moviedetails.data.DetailsRepositoryImp
import com.cactus.themovie.moviedetails.data.DetailsService
import com.cactus.themovie.moviedetails.domain.SimilarMovie
import com.cactus.themovie.moviedetails.presentation.DetailsViewModel
import com.cactus.themovie.network.domain.Movie
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val dispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var repository: DetailsRepository

    @MockK
    private lateinit var service: DetailsService

    @MockK
    private lateinit var movieLiveDataObserver: Observer<Movie>

    @MockK
    private lateinit var showNotificationErrorLiveDataObserver: Observer<String>

    @MockK
    private lateinit var similarMoviesLiveDataObserver: Observer<MutableList<SimilarMovie>>

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        repository = DetailsRepositoryImp(service)
        viewModel = instantiateViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewmodel getMovie get success then sets movieLiveData`() {
        //  Arrange
        val movie = Movie(idtest)
        coEvery { service.getMovie(movieId) } returns Response.success(movie)

        //  Act
        viewModel.getMovie()

        //  Assert
        coVerify { movieLiveDataObserver.onChanged(movie) }
    }


    @Test
    fun `when viewmodel getMovie get error then show notification error`() {
        //  Arrange
        val errorBody =
            "{\"errors\": [\"Unexpected parameter\"]}".toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { service.getMovie(movieId) } returns Response.error(404, errorBody)

        //  Act
        viewModel.getMovie()

        //  Assert
        coVerify { showNotificationErrorLiveDataObserver
            .onChanged("Network call has failed for a following reason:  404 Response.error()") }
    }


    private fun instantiateViewModel(): DetailsViewModel {
        val viewModel = DetailsViewModel(repository)
        viewModel.apply {
            movieLiveData.observeForever(movieLiveDataObserver)
            showNotificationErrorLiveData.observeForever(showNotificationErrorLiveDataObserver)
            similarMoviesLiveData.observeForever(similarMoviesLiveDataObserver)
        }
        return viewModel
    }


}