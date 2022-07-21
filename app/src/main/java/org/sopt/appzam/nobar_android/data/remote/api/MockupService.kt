package org.sopt.appzam.nobar_android.data.remote.api

import org.sopt.appzam.nobar_android.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("search/keyword")
    fun getSearchResult(
        @Query("keyword") keyword : String
    ):Call<SearchResultResponse>

    @GET("recipe/{recipeId}")
    fun getRecipeDetail(
        @Path("recipeId")recipeId :String
    ):Call<RecipeDetailResponse>
}