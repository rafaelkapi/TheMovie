package com.cactus.movie.moviedetails.data

import com.cactus.movie.moviedetails.data.model.GenresListResponse
import com.cactus.movie.moviedetails.data.model.MovieResponse
import com.cactus.movie.moviedetails.data.model.SimilarMoviesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int?): Response<MovieResponse>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int?): Single<SimilarMoviesResponse>

    @GET("/3/genre/movie/list")
    suspend fun getGenresList(): Single<GenresListResponse>


}