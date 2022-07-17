package org.sopt.appzam.nobar_android.data.local.db

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.appzam.nobar_android.data.local.entity.KakaoUser
import org.sopt.appzam.nobar_android.data.local.entity.NobarAccessToken

class AuthTokenManager private constructor(
        private val sharedPreferences: SharedPreferences,
) {
    fun saveKakaoUserInfo(kakaoUser: KakaoUser) {
        sharedPreferences.edit {
            putString(KEY_KAKAO_USER_ID, kakaoUser.id)
            putString(KEY_KAKAO_USER_NICKNAME, kakaoUser.nickname)
            putString(KEY_KAKAO_USER_ACCESS_TOKEN, kakaoUser.accessToken)
            putString(KEY_KAKAO_USER_REFRESH_TOKEN, kakaoUser.refreshToken)
            putString(KEY_KAKAO_USER_PROFILE_IMAGE_URL, kakaoUser.profileImageUrl)
            putString(KEY_KAKAO_USER_THUMBNAIL_IMAGE_URL, kakaoUser.thumbnailImageUrl)
        }
    }

    fun hasKakaoUserInfo(): Boolean {
        return sharedPreferences.getString(KEY_KAKAO_USER_ID, null) != null
    }

    fun getKakaoUserInfo(): KakaoUser? {
        return KakaoUser(
                id = sharedPreferences.getString(KEY_KAKAO_USER_ID, null) ?: return null,
                nickname = sharedPreferences.getString(KEY_KAKAO_USER_NICKNAME, null) ?: return null,
                accessToken = sharedPreferences.getString(KEY_KAKAO_USER_ACCESS_TOKEN, null) ?: return null,
                refreshToken = sharedPreferences.getString(KEY_KAKAO_USER_REFRESH_TOKEN, null) ?: return null,
                profileImageUrl = sharedPreferences.getString(KEY_KAKAO_USER_PROFILE_IMAGE_URL, null),
                thumbnailImageUrl = sharedPreferences.getString(KEY_KAKAO_USER_THUMBNAIL_IMAGE_URL, null),
        )
    }

    fun getNobarToken(): NobarAccessToken? {
        return NobarAccessToken(
                accessToken = sharedPreferences.getString(KEY_NOBAR_USER_ACCESS_TOKEN, null) ?: return null,
        )
    }

    fun saveNobarToken(nobarUser: NobarAccessToken) {
        sharedPreferences.edit {
            putString(KEY_NOBAR_USER_ACCESS_TOKEN, nobarUser.accessToken)
        }
    }

    companion object {
        private const val KEY_NOBAR_USER_ACCESS_TOKEN = "KEY_NOBAR_USER_ACCESS_TOKEN"

        private const val KEY_KAKAO_USER_ID = "KEY_KAKAO_USER_ID"
        private const val KEY_KAKAO_USER_ACCESS_TOKEN = "KEY_KAKAO_USER_ACCESS_TOKEN"
        private const val KEY_KAKAO_USER_REFRESH_TOKEN = "KEY_KAKAO_USER_REFRESH_TOKEN"
        private const val KEY_KAKAO_USER_NICKNAME = "KEY_KAKAO_USER_NICKNAME"
        private const val KEY_KAKAO_USER_PROFILE_IMAGE_URL = "KEY_KAKAO_USER_PROFILE_IMAGE_URL"
        private const val KEY_KAKAO_USER_THUMBNAIL_IMAGE_URL = "KEY_KAKAO_USER_THUMBNAIL_IMAGE_URL"

        @Volatile
        private var instance: AuthTokenManager? = null

        @Synchronized
        fun getInstance(context: Context): AuthTokenManager {
            return AuthTokenManager(
                    context.getSharedPreferences("nobar_shared_preference", Context.MODE_PRIVATE)
            ).also { this.instance = it }
        }
    }
}
