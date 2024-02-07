package com.cactus.movie.moviedetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cactus.commons.base.BaseViewModel
import com.cactus.commons.livedata.ViewState
import com.cactus.movie.moviedetails.domain.DetailsInteractor
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo
import com.cactus.network.qualifiers.CommonsIoScheduler
import com.cactus.network.qualifiers.CommonsMainScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    @CommonsIoScheduler private val ioScheduler: Scheduler,
    @CommonsMainScheduler private val mainScheduler: Scheduler,
    private val interactor: DetailsInteractor
) : BaseViewModel() {

    private val _state = MutableLiveData<ViewState>()
    internal val state: LiveData<ViewState> get() = _state

    private val _similarMovies = MutableLiveData<List<SimilarMoviesVo>>()
    internal val similarMovies: LiveData<List<SimilarMoviesVo>> get() = _similarMovies

    override fun onCreate() {
        super.onCreate()
        getMovie()
        getSimilarMovies()
    }

    private fun getMovie() {
        interactor.getMovieDetails(ID_MOVIE)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe { _state.value = ViewState.Loading }
            .subscribe(
                {
                    _state.value = ViewState.Success(it)
                }, {
                    _state.value = ViewState.Error()
                }
            )
            .also { compositeDisposable.add(it) }
    }

    private fun getSimilarMovies() {
        interactor.getSimilarMovies(ID_MOVIE)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                {
                    _state.value = ViewState.Success(it)
                }, {
                    _state.value = ViewState.Error()
                }
            )
            .also { compositeDisposable.add(it) }
    }

    companion object {
        private const val ID_MOVIE = 118340

    }

}