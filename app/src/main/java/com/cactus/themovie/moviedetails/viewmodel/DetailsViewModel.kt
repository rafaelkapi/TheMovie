package com.cactus.themovie.moviedetails.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cactus.themovie.common.OperationResult
import com.cactus.themovie.common.base.BaseViewModel
import com.cactus.themovie.moviedetails.data.DetailsRepository
import com.cactus.themovie.network.model.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : BaseViewModel() {


    override fun onCreate() {
        super.onCreate()

    }


     fun getMovie() {
        viewModelScope.launch {
            repository.getMovie(ID_MOVIE).let { response ->
                when (response)  {
                    is OperationResult.Success<*> -> {
                        val movie = response.data as Movie
                        Log.d("Teste","${movie.title}")
                    }
                    is OperationResult.Error -> {
                    }
                    else -> {}
                }
            }
        }
    }

    companion object {
        private const val ID_MOVIE = 20
    }

}