package com.cactus.themovie.network.domain


data class Movie(
    val id: Int? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val backdropUrl: String = "https://image.tmdb.org/t/p/original$backdrop_path",
    val posterUrl: String = "https://image.tmdb.org/t/p/original$poster_path",
    val title: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    val genre_ids: List<Int>? = null,
    val release_date: String? = null,
    val vote_count: Int? = null
)

data class SimilarMovies(
    val results: List<Movie>? = null
    )

data class GenresList(
    val genres: List<Genre>
)

data class Genre (
    val id: Int? = null,
    val name: String? = null
        )
