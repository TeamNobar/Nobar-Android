package org.sopt.appzam.nobar_android.data.local.entity

data class NobarAccessToken(
        val accessToken: String,
) {
    fun isExpired(): Boolean {
        return false
    }
}
