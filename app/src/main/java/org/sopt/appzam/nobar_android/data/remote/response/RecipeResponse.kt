package org.sopt.appzam.nobar_android.data.remote.response

import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.data.remote.response.common.GlassModel
import org.sopt.appzam.nobar_android.data.remote.response.common.IngredientDetailModel
import org.sopt.appzam.nobar_android.data.remote.response.common.SkillModel

data class RecipeResponse(
    val id : String,
    val name : String,
    val enName : String,
    val version : List<String>,
    val base : BaseModel,
    val proof : Int,
    val skill : SkillModel,
    val glass : GlassModel,
    val ingredients : List<IngredientDetailModel>,
    val steps : List<String>,
<<<<<<< HEAD
    val defaultRecipe : String?,
=======
    val defaultRecipe : String,
>>>>>>> 8ab950c741bd0f26638f8bd3817c3f5c1bf79785
    val proofIcon : String
)
