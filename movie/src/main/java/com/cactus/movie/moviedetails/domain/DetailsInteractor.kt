package com.cactus.movie.moviedetails.domain

import com.cactus.movie.moviedetails.data.DetailsRepository
import com.cactus.movie.moviedetails.data.model.toSimilarVo
import com.cactus.movie.moviedetails.data.model.toVo
import com.cactus.movie.moviedetails.presentation.model.MovieDetailsVo
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo
import io.reactivex.Single
import org.w3c.dom.Text
import javax.inject.Inject

interface DetailsInteractor {
    fun getMovieDetails(id: Int): Single<MovieDetailsVo>
    fun getSimilarMovies(id: Int): Single<List<SimilarMoviesVo>>
}

class DetailsInteractorImpl @Inject constructor(
    private val repository: DetailsRepository
) : DetailsInteractor {
    override fun getMovieDetails(id: Int) = repository.getMovie(id).map { it.toVo() }
    override fun getSimilarMovies(id: Int): Single<List<SimilarMoviesVo>> {
        val genreCatalog = repository.getGenresList().genres.associate { it.id to it.name }

        return repository.getSimilarMovies(id).map { similarMoviesResponse ->
            similarMoviesResponse.results?.map { movieResponse ->
                val movieGenres = mutableListOf<String>()
                movieResponse.genre_ids?.forEach { id -> genreCatalog[id]?.let { movieGenres.add(it) } }
                movieResponse.toSimilarVo().copy(movieGenres = normalizedText(movieGenres))
            }
        }
    }

    private fun normalizedText(textList: List<String>): String {
        var outputText = ""
        textList.forEach { text ->
            outputText = "$outputText, $text"
        }
        return outputText
    }
}