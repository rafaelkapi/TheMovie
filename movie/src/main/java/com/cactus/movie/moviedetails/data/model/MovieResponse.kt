package com.cactus.movie.moviedetails.data.model


data class MovieResponse(
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

data class SimilarMoviesResponse(
    val results: List<MovieResponse>? = null
    )

data class GenresListResponse(
    val genres: List<Genre>
)

data class Genre (
    val id: Int? = null,
    val name: String? = null
        )
