package org.sopt.appzam.nobar_android.data.remote.response.common

data class IngredientResponse(
    val id : String,
    val name : String,
    val enName : String,
    val proof : Int,
    val category : String,
    val quantity : String?
)