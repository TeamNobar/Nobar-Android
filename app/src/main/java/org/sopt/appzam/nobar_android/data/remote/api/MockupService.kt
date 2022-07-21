package org.sopt.appzam.nobar_android.data.remote.api

import org.sopt.appzam.nobar_android.data.remote.response.BaseResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchKeywordsResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MockupService {
    @GET("/search/tag")
    fun getBaseTag(): Call<BaseResponse>

    @GET("/search/base")
    fun getBaseSearch(
        @Query("base") base: String
    ): Call<SearchResultResponse>

    @GET("/search")
    fun getSearchKeywords(): Call<SearchKeywordsResponse>

    @GET("search/keyword")
    fun getSearchResult(
        @Query("keyword") keyword : String
    ):Call<SearchResultResponse>
}