package org.sopt.appzam.nobar_android.data.remote.response

data class GuideResponse(
    val id : String,
    val title : String,
    val subTitle : String,
    val contents : String,
    val images : List<String>,
    val tumbNail : String
)
