package org.sopt.appzam.nobar_android.data.remote.response

import org.sopt.appzam.nobar_android.data.remote.response.common.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.common.RecommendModel

data class SearchKeywordsResponse(
    val recommends: List<RecommendModel>,
    val recipes: List<RecipeResponse>,
    val ingredients: List<IngredientResponse>
)
