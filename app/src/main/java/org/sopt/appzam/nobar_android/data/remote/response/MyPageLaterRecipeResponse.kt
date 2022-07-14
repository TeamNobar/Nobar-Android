package org.sopt.appzam.nobar_android.data.remote.response

data class MyPageLaterRecipeResponse(
    val id : String,
    val name : String,
    val enName : String,
    val version : List<String>,
    val base : String,
    val proof : Int,
    val skill : String,
    val glass : String,
    val ingredients : String,
    val steps : List<String>
)
