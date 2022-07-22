package org.sopt.appzam.nobar_android.data.remote.response

data class TagResponse(
    val id : String,
    val content : String,
    val icon : String,
    var isSelected : Boolean
)