package com.cactus.movie.moviedetails.data

import com.cactus.movie.moviedetails.data.model.GenresListResponse
import com.cactus.movie.moviedetails.data.model.MovieResponse
import com.cactus.movie.moviedetails.data.model.SimilarMoviesResponse
import io.reactivex.Single
import javax.inject.Inject

interface DetailsRepository {
    suspend fun getMovie(id: Int): Single<MovieResponse>
    suspend fun getSimilarMovies(id: Int): Single<SimilarMoviesResponse>
    suspend fun getGenresList(): Single<GenresListResponse>
}

class DetailsRepositoryImp @Inject constructor(
    private val service: DetailsService,
) : DetailsRepository {

    companion object {
        private const val NOT_AUTHORIZED_CODE = 401
        private const val SERVER_ERROR = 500
    }

    override suspend fun getMovie(id: Int): Single<MovieResponse> {
        val movieResponse = service.getMovie(id)
        return when (movieResponse.code()) {
            NOT_AUTHORIZED_CODE -> Single.error(Throwable(message = "We're experiencing some problems. Code: $NOT_AUTHORIZED_CODE" ))
            SERVER_ERROR -> Single.error(Throwable(message = "We're experiencing some problems. Please try again later." ))
            else -> Single.just(movieResponse.body())
        }
    }

    override suspend fun getSimilarMovies(id: Int): Single<SimilarMoviesResponse> = service.getSimilarMovies(id)

    override suspend fun getGenresList(): Single<GenresListResponse> = service.getGenresList()
}