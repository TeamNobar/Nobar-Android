package org.sopt.appzam.nobar_android.data.remote.params

import org.sopt.appzam.nobar_android.data.remote.response.TagResponse

data class TastingNoteParams(
    val recipeId : String,
    val rate : Float,
    val tagList : List<TagResponse>,
    val tasteContent : String?,
    val experienceContent : String?,
    val createdAt : String
)