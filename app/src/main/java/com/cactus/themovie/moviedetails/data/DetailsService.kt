package com.cactus.themovie.moviedetails.data

import com.cactus.themovie.network.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") characterId: Int?): Response<Movie>



}