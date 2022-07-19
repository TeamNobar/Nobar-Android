package org.sopt.appzam.nobar_android.data.remote.utils

import org.sopt.appzam.nobar_android.data.remote.response.NobarErrorResponse
import org.sopt.appzam.nobar_android.data.remote.service.exception.NobarNetworkException
import retrofit2.Response

fun <T> Response<T>.onSuccess(block: (T) -> Unit): Response<T> {
    if (this.isSuccessful) {
        val body = this.body() ?: error("body cannot be null when response is successful. body : ${body()}")
        block(body)
    }
    return this
}

fun <T> Response<T>.onFailure(block: (t: Throwable) -> Unit): Response<T> {
    if (!this.isSuccessful) {
        val errorBody = this.errorBody() ?: error("errorBody cannot be null when response is not successful. errorBody : ${errorBody()}")
        val errorResponse = NobarErrorResponse.from(errorBody.string())
        val nobarNetworkException = NobarNetworkException(errorResponse, errorResponse.status, errorResponse.message)
        block(nobarNetworkException)
    }
    return this
}
