package org.sopt.appzam.nobar_android.data.remote.response

data class MyPageResponse(
    val nickName : String,
    val laterRecipes : List<RecipeResponse>,
    val tastingNotes : List<TastingNoteResponse>
)
