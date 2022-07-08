package org.sopt.appzam.nobar_android.data

sealed class HomeData {
    data class Title(
        val subTitle: String,
        val mainTitle: String
    ) : HomeData()

    data class LateRecipe(
        val koreanTitle: String,
        val englishTitle: String,
        val base: String
    ) : HomeData()

    data class CocktailGuide(
        val image : String,
        val guide : String
    ) : HomeData()

    data class NobarRecipe(
        val content : String
    )
}