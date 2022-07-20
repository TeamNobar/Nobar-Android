package org.sopt.appzam.nobar_android.data.remote.response.api

import org.sopt.appzam.nobar_android.data.remote.response.BaseResponse
import org.sopt.appzam.nobar_android.data.remote.response.HomeResponse
import org.sopt.appzam.nobar_android.data.remote.response.MyPageResponse
import retrofit2.Call
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import retrofit2.http.GET

interface MockupService {
    @GET("/search/tag")
    fun getBaseTag(): Call<BaseResponse>

    @GET("/home")
    fun getHomeItem(): Call<HomeResponse>

    @GET("/mypage")
    fun getMyPageItem(): Call<MyPageResponse>
}