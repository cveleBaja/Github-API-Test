package com.cvetkovicmilan.githubapitest.data.remote.network

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()

    data class Failure<out T>(val message: String) : ResponseWrapper<T>()
}