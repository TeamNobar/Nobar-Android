package org.sopt.appzam.nobar_android.data.remote.response

import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.data.remote.response.common.GlassModel
import org.sopt.appzam.nobar_android.data.remote.response.common.IngredientDetailModel
import org.sopt.appzam.nobar_android.data.remote.response.common.SkillModel

data class MyPageTastingRecipesResponse(
    val id : String,
    val name : String,
    val enName : String,
    val version : List<String>,
    val base : BaseModel,
    val proof : Int,
    val skill : SkillModel,
    val glass : GlassModel,
    val ingredients : List<IngredientDetailModel>,
    val steps : List<String>
)
