package com.cactus.movie.moviedetails.domain.model

sealed class OperationResult  {
    class Success<T>(val data: T) : OperationResult()
    object Loading : OperationResult()
    class Error(val error: String?) : OperationResult()
 }


