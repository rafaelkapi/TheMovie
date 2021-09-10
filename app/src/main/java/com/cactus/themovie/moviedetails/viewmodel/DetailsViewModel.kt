package com.cactus.themovie.moviedetails.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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

    val movieLiveData = MutableLiveData<Movie>()
    val popularityLiveData = MutableLiveData<String>()
    val likesLiveData = MutableLiveData<String>()
    val similarMoviesLiveData = MediatorLiveData<List<Movie>>()


    override fun onCreate() {
        super.onCreate()

    }


    fun getMovie() {
        viewModelScope.launch {
            repository.getMovie(ID_MOVIE).let { response ->
                when (response) {
                    is OperationResult.Success<*> -> {
                        val movie = response.data as Movie
                        movieLiveData.value = movie
                        movie.vote_count?.let { likesFormat(it) }
                        movie.popularity?.let { popularityFormat(it) }
                        Log.d("Teste", "${movie.title}")
                    }
                    is OperationResult.Error -> {
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun likesFormat(likes: Int) {

        if (likes >= 1000) {
            likesLiveData.value = "  ${likes / 1000}.${likes % 1000 / 100}K Likes"

        } else likesLiveData.value = "  $likes Likes"
    }

    private fun popularityFormat(popularity: Float) {
        popularityLiveData.value = "  ${String.format("%.1f", popularity)}% Popularity"
    }


    companion object {
        private const val ID_MOVIE = 115

    }

}