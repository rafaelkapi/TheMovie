package com.cactus.movie.moviedetails.domain

import com.cactus.movie.moviedetails.data.DetailsRepository
import com.cactus.movie.moviedetails.data.model.MovieResponse
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
    override fun getMovieDetails(id: Int) = repository.getMovie(id).map { toMovieDetailsVo(it) }
    override fun getSimilarMovies(id: Int): Single<List<SimilarMoviesVo>> {

        return repository.getGenresList().flatMap { genreListResponse ->
            val genreCatalog = genreListResponse.genres.associate { it.id to it.name }
            repository.getSimilarMovies(id).map { similarMoviesResponse ->
                similarMoviesResponse.results?.map { movieResponse ->
                    val movieGenres = mutableListOf<String>()
                    movieResponse.genre_ids?.forEach { id ->
                        genreCatalog[id]?.let { movieGenres.add(it) }
                    }
                    movieResponse.toSimilarVo().copy(
                        movieGenres = normalizedText(movieGenres),
                        releaseDate = setReleaseYear(movieResponse.release_date ?: "")
                    )
                }
            }
        }
    }

    private fun normalizedText(textList: List<String>): String {
        var outputText = ""
        textList.forEachIndexed { index, genre ->
            outputText += if (index != textList.size - 1) "$genre, "
            else genre
        }
        return outputText
    }

    private fun toMovieDetailsVo(response: MovieResponse) = MovieDetailsVo(
        title = response.title ?: "",
        subtitle = response.overview ?: "",
        likes = likesFormat(response.vote_count ?: 0),
        popularity = popularityFormat(response.popularity),
        posterUrl = response.posterUrl
    )

    private fun setReleaseYear(date: String): String {
        val pattern = "\\d{4}".toRegex()
        val found = pattern.find(date)
        return found?.value ?: ""
    }

    private fun likesFormat(likes: Int) =
        if (likes >= 1000)
            "  ${likes / 1000}.${likes % 1000 / 100}K Likes"
        else "  $likes Likes"

    private fun popularityFormat(popularity: Float?) =
        "  ${String.format("%.1f", popularity)}% Popularity"
}