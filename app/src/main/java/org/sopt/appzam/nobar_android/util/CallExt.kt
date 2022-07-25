package org.sopt.appzam.nobar_android.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.clone().enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("server", "error:$t")
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
                Log.d("status",response.code().toString())
                Log.d("server", "성공~!")
            } else {
                onError?.invoke(response.code())
                Log.d("status",response.code().toString())
            }
        }
    })
}