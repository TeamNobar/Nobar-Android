package org.sopt.appzam.nobar_android.presentation.onboarding.nickname

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.local.entity.NobarAccessToken
import org.sopt.appzam.nobar_android.data.remote.api.MockupService
import org.sopt.appzam.nobar_android.data.remote.params.LoginParams
import org.sopt.appzam.nobar_android.data.remote.utils.onFailure
import org.sopt.appzam.nobar_android.data.remote.utils.onSuccess
import org.sopt.appzam.nobar_android.presentation.base.NobarViewModel

class RegisterNicknameViewModel(
        private val authTokenManager: AuthTokenManager,
        private val mockupService: MockupService,
) : NobarViewModel() {
    val nickname = MutableLiveData("")

    fun isLoginRunning(): Boolean = isLoading.value ?: false

    fun login() = viewModelScope.launch {
        startLoading()

        val currentNickname = nickname.value.orEmpty()
        mockupService.login(LoginParams(currentNickname))
                .onSuccess {
                    authTokenManager.saveNobarToken(NobarAccessToken(it.accesstoken))
                    notifySuccess()
                }
                .onFailure {
                    // 실풰
                }
        stopLoading()
    }
}
