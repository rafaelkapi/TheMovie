package com.cactus.themovie.network

import com.cactus.themovie.common.OperationResult
import retrofit2.Response


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): OperationResult {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return OperationResult.Success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun error(message: String): OperationResult {
        return OperationResult.Error("Network call has failed for a following reason: $message")
    }

}

