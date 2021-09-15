package com.cactus.themovie.moviedetails.data

import com.cactus.themovie.network.domain.GenresList
import com.cactus.themovie.network.domain.Movie
import com.cactus.themovie.network.domain.SimilarMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int?): Response<Movie>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int?): Response<SimilarMovies>

    @GET("/3/genre/movie/list")
    suspend fun getGenresList(): Response<GenresList>


}