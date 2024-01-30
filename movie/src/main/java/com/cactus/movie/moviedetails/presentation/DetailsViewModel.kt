package com.cactus.movie.moviedetails.presentation
import androidx.lifecycle.MutableLiveData
import com.cactus.commons.base.BaseViewModel
import com.cactus.movie.moviedetails.data.DetailsRepository
import com.cactus.movie.moviedetails.domain.model.SimilarMovie
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : BaseViewModel() {

    // LiveDatas
    val movieLiveData = MutableLiveData<Movie>()
    val popularityLiveData = MutableLiveData<String>()
    val likesLiveData = MutableLiveData<String>()
    val showNotificationErrorLiveData = MutableLiveData<String>()
    val genresListLiveData = MutableLiveData<MutableMap<Int, String>>()
    val similarMoviesLiveData = MutableLiveData<MutableList<SimilarMovie>>()

    // Calls
    var loadingShow: CallSimple = null
    var loadingHide: CallSimple = null
    var emptyContainerView: ((show: Boolean) -> Unit)? = null
    var loadingSimilarMovieHide: CallSimple = null
    var startAnimationLike: CallSimple = null


    fun getMovie() {
        loadingShow?.invoke()
        viewModelScope.launch {
            repository.getMovie(ID_MOVIE).let { response ->
                when (response) {
                    is OperationResult.Success<*> -> {
                        emptyContainerView?.invoke(false)
                        val movie = response.data as Movie
                        movieLiveData.value = movie

                        movie.vote_count?.let { likesFormat(it) }
                        movie.popularity?.let { popularityFormat(it) }
                    }
                    is OperationResult.Error -> {
                        showNotificationErrorLiveData.value = response.error ?: ""
                        loadingSimilarMovieHide?.invoke()
                    }
                    else -> {}
                }
            }
            loadingHide?.invoke()
            getGenreList()
            getSimilarMovies()
        }
    }


    private suspend fun getGenreList() {
            repository.getGenresList().let { response ->
                when (response) {
                    is OperationResult.Success<*> -> {
                        val genresList = response.data as GenresList
                        val listAux: MutableMap<Int, String> = mutableMapOf()
                        genresList.genres.forEach { genre ->
                            listAux.put((genre.id ?: 0), (genre.name ?: ""))
                        }
                        genresListLiveData.value = listAux
                    }
                    is OperationResult.Error -> {
                        showNotificationErrorLiveData.value = response.error ?: ""
                    }
                    else -> {}
                }
            }
    }

    private suspend fun getSimilarMovies() {
            repository.getSimilarMovies(ID_MOVIE).let { response ->
                when (response) {
                    is OperationResult.Success<*> -> {
                        val similarMovies = response.data as SimilarMovies
                        loadListSimilarMovies(similarMovies)
                        loadingSimilarMovieHide?.invoke()
                    }
                    is OperationResult.Error -> {
                        showNotificationErrorLiveData.value = response.error ?: ""
                    }
                    else -> {}
                }
            }
    }

    private fun loadListSimilarMovies(similarMovies: SimilarMovies) {
        val listOut: MutableList<SimilarMovie> = mutableListOf()
        similarMovies.results?.forEach { movie ->
            listOut.add(
                SimilarMovie(
                    title = movie.title,
                    posterUrl = movie.posterUrl,
                    releaseDate = movie.release_date,
                    genres = movie.genre_ids?.let { buildGenresString(it) }
                )
            )
        }
        similarMoviesLiveData.value = listOut
    }

    private fun buildGenresString(ids: List<Int>): String {
        var textOut = ""
        ids.forEach { id ->
            val genre = genresListLiveData.value?.get(id)
            textOut += if (textOut.isBlank()) {
                genre
            } else ", $genre"
        }
        return textOut
    }

    private fun likesFormat(likes: Int) {
        if (likes >= 1000) {
            likesLiveData.value = "  ${likes / 1000}.${likes % 1000 / 100}K Likes"
        } else likesLiveData.value = "  $likes Likes"
    }

    fun onClickLike() {
        startAnimationLike?.invoke()
    }

    private fun popularityFormat(popularity: Float) {
        popularityLiveData.value = "  ${String.format("%.1f", popularity)}% Popularity"
    }

    companion object {
        private const val ID_MOVIE = 118340

    }

}