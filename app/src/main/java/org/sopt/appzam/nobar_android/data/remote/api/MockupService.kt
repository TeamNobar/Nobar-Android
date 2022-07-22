package org.sopt.appzam.nobar_android.data.remote.api

import org.sopt.appzam.nobar_android.data.remote.params.TastingNoteParams
import org.sopt.appzam.nobar_android.data.remote.params.LoginParams
import org.sopt.appzam.nobar_android.data.remote.response.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MockupService {
    @GET("/search/tag")
    fun getBaseTag(): Call<BaseResponse>

    @GET("/home")
    fun getHomeItem(): Call<HomeResponse>

    @GET("/guide/{guideId}")
    fun getGuideDetail(
        @Path("guideId") guideId: String?
    ): Call<GuideResponse>

    @GET("/recipe/{recipeId}")
    fun getRecipeDetail(
        @Path("recipeId") recipeId: String?
    ): Call<RecipeDetailResponse>

    @GET("/mypage")
    fun getMyPageItem(): Call<MyPageResponse>

    @GET("/search/base")
    fun getBaseSearch(
        @Query("base") base: String
    ): Call<SearchResultResponse>

    @GET("/search")
    fun getSearchKeywords(): Call<SearchKeywordsResponse>

    @GET("/search/keyword")
    fun getSearchResult(
        @Query("keyword") keyword: String
    ): Call<SearchResultResponse>

    @PATCH("/recipe/{recipeId}")
    fun patchScrap(
        @Path("recipeId") recipeId: String,
        @Body body: Boolean
    ): Call<RecipeDetailResponse>

    @GET("/note/tag")
    fun getTagList(): Call<List<TagResponse>>

    @GET("/note/{tastingNoteId}")
    fun getNote(
        @Path("tastingNoteId") tastingNoteId: String
    ): Call<TastingNoteResponse>

    @POST("/note")
    fun postNote(
        @Body body: TastingNoteParams
    ): Call<TastingNoteResponse>

    @POST("/auth")
    suspend fun login(
        @Body loginParams: LoginParams,
    ): Response<LoginResponse>
}
