package org.sopt.appzam.nobar_android.data.remote.response

data class HomeResponse(
    val laterRecipeList : List<RecipeResponse>,
    val guideList : List<GuideResponse>,
    val noBarRecipes : List<NobarRecipeResponse>
)
