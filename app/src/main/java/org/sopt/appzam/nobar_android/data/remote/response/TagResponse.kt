package org.sopt.appzam.nobar_android.data.remote.response

data class TagResponse(
    val id : Int,
    val content : String,
    val activeIcon : String,
    val inActiveIcon : String,
    var isSelected : Boolean
)