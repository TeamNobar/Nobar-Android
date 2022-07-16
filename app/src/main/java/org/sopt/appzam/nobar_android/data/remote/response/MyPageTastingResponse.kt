package org.sopt.appzam.nobar_android.data.remote.response

data class MyPageTastingResponse(
    val id : String,
    val rate : Int,
    val title : String,
    val recipe : String,
    val tag                                                                                   : List<String>,
    val tasteContent : String,
    val experienceContent : String,
    val createdAt : Int
)
