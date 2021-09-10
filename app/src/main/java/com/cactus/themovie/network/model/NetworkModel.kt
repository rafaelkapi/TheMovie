package com.cactus.themovie.network.model


data class Movie(
    val id: Int? = null,
    val backdrop_path: String? = null,
    val posterUrl: String = "https://image.tmdb.org/t/p/original$backdrop_path",
    val title: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val popularity: Float? = null,
    val vote_count: Int? = null
)

data class similarMovies(val results: List<Movie>)
