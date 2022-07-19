package org.sopt.appzam.nobar_android.data.remote.response.api

import org.sopt.appzam.nobar_android.data.remote.response.BaseResponse
import retrofit2.Call
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import retrofit2.http.GET

interface MockupService {
    @GET("/search/tag")
    fun getBaseTag():Call<BaseResponse>
}