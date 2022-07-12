package org.sopt.appzam.nobar_android.data.remote.response

data class MyPageResponse(
    val nickName : String,
    val laterRecipes : List<String>,
    val tastingNotes : List<String>
)
