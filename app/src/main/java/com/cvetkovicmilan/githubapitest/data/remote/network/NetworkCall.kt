package com.cvetkovicmilan.githubapitest.data.remote.network

import com.cvetkovicmilan.githubapitest.util.Constants
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T> =
    withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            ResponseWrapper.Success(response)
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> ResponseWrapper.Failure(exception.message.toString())
                is HttpException -> parseErrorBody(exception)
                else -> ResponseWrapper.Failure(Constants.GENERIC_ERROR_MESSAGE)
            }
        }
    }

fun <T> parseErrorBody(httpException: HttpException): ResponseWrapper<T> {
    val errorBody = httpException.response()?.errorBody()?.string()
    val gsonError = Gson().fromJson(errorBody, ApiError::class.java)

    return ResponseWrapper.Failure(gsonError.message)
}