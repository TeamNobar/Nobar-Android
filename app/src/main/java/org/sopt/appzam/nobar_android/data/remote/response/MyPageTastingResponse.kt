package org.sopt.appzam.nobar_android.data.remote.response

data class MyPageTastingResponse(
    val id : String,
    val rate : Int,
    val title : String,
    val recipe : RecipeResponse,
    val tag : List<Int>,
    val tasteContent : String,
    val experienceContent : String,
    val createdAt : Int
)
