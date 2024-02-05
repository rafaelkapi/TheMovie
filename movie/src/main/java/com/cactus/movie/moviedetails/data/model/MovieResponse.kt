package com.cactus.movie.moviedetails.data.model

import com.cactus.movie.moviedetails.presentation.model.MovieDetailsVo
import com.cactus.movie.moviedetails.presentation.model.SimilarMoviesVo


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

fun MovieResponse.toVo() = MovieDetailsVo(
    title = title ?: "",
    subtitle = overview ?: "",
    likes = vote_count.toString(),
    popularity = popularity.toString(),
    posterUrl = posterUrl
)

data class SimilarMoviesResponse(
    val results: List<MovieResponse>? = null
    )

fun SimilarMoviesResponse.toVo() = results?.map { movieResponse ->
    SimilarMoviesVo(
        title = movieResponse.title ?: "",
        releaseDate = movieResponse.release_date ?: "",
        movieGenres = "",
        posterUrl = movieResponse.posterUrl
    )
}

fun MovieResponse.toSimilarVo() =
    SimilarMoviesVo(
        title = title ?: "",
        releaseDate = release_date ?: "",
        movieGenres = "",
        posterUrl = posterUrl
    )


data class GenresListResponse(
    val genres: List<Genre>
)

data class Genre (
    val id: Int? = null,
    val name: String? = null
        )
