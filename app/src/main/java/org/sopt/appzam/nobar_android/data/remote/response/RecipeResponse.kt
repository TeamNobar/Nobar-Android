package org.sopt.appzam.nobar_android.data.remote.response

data class RecipeResponse(
    val koreanName : String,
    val englishName : String,
    val bookmark : Boolean,
    val ingredients : List<Ingredient>,
    val steps : List<String>,
    val suggestion : String
){
    data class Ingredient(
        val title : String,
        val kind : String,
        val amount : String
    )
}
