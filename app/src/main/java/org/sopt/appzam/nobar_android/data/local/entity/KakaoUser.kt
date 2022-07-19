package org.sopt.appzam.nobar_android.data.local.entity

data class KakaoUser(
        val id: String,
        val accessToken: String,
        val refreshToken: String,
        val nickname: String,
        val profileImageUrl: String? = null,
        val thumbnailImageUrl: String? = null,
)
