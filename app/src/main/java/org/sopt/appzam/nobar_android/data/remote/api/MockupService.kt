package org.sopt.appzam.nobar_android.data.remote.api

import org.sopt.appzam.nobar_android.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MockupService {
    @GET("/search/tag")
    fun getBaseTag(): Call<BaseResponse>

    @GET("/home")
    fun getHomeItem(): Call<HomeResponse>

    @GET("/mypage")
    fun getMyPageItem(): Call<MyPageResponse>

    @GET("/search/base")
    fun getBaseSearch(
        @Query("base") base: String
    ): Call<SearchResultResponse>

    @GET("/search")
    fun getSearchKeywords(): Call<SearchKeywordsResponse>
}