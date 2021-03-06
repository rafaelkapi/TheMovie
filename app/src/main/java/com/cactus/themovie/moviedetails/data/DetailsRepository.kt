package com.cactus.themovie.moviedetails.data

import com.cactus.themovie.common.OperationResult
import com.cactus.themovie.network.BaseDataSource
import javax.inject.Inject

interface DetailsRepository {
    suspend fun getMovie(id: Int): OperationResult
    suspend fun getSimilarMovies(id: Int): OperationResult
    suspend fun getGenresList(): OperationResult
}

class DetailsRepositoryImp @Inject constructor(
    private val service: DetailsService,
) : BaseDataSource(), DetailsRepository {

    override suspend fun getMovie(id: Int): OperationResult = getResult { service.getMovie(id) }

    override suspend fun getSimilarMovies(id: Int): OperationResult = getResult {service.getSimilarMovies(id)}

    override suspend fun getGenresList(): OperationResult = getResult {service.getGenresList()}


}