package org.sopt.appzam.nobar_android.data.remote.service.exception

import org.sopt.appzam.nobar_android.data.remote.response.NobarErrorResponse

data class NobarNetworkException(
        val nobarErrorResponse: NobarErrorResponse,
        val statusCode: Int,
        override val message: String,
) : RuntimeException(message)
