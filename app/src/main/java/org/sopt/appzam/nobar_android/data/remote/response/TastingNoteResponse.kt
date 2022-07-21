package org.sopt.appzam.nobar_android.data.remote.response

data class TastingNoteResponse(
    val id: String,
    val rate: Double,
    val title: String,
    val recipe: RecipeResponse,
    val tag: List<TagResponse>,
    val tasteContent: String,
    val experienceContent: String,
    val createdAt: String
)
