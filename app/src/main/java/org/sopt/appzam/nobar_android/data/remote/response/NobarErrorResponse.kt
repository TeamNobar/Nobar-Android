package org.sopt.appzam.nobar_android.data.remote.response

import org.json.JSONObject

data class NobarErrorResponse(
        val status: Int,
        val message: String,
) {
    companion object {
        private const val STATUS = "STATUS"
        private const val MESSAGE = "MESSAGE"

        fun from(errorJsonString: String): NobarErrorResponse {
            val errorJson = runCatching { JSONObject(errorJsonString) }.getOrNull()

            return if (errorJson != null) {
                NobarErrorResponse(
                        status = errorJson.getInt(STATUS),
                        message = errorJson.getString(MESSAGE),
                )
            } else {
                NobarErrorResponse(
                        status = -1,
                        message = "Unknown Error. message : $errorJsonString"
                )
            }
        }
    }
}
