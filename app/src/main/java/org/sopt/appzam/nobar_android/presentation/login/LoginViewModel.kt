package org.sopt.appzam.nobar_android.presentation.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.local.entity.NobarAccessToken
import org.sopt.appzam.nobar_android.data.remote.params.LoginParams
import org.sopt.appzam.nobar_android.data.remote.service.NobarService
import org.sopt.appzam.nobar_android.data.remote.utils.onFailure
import org.sopt.appzam.nobar_android.data.remote.utils.onSuccess

class LoginViewModel(
        private val authTokenManager: AuthTokenManager,
        private val nobarService: NobarService,
) : ViewModel() {
    val nickname = MutableLiveData("Malibin")

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun isLoginRunning(): Boolean = isLoading.value ?: false

    fun login() = viewModelScope.launch {
        _isLoading.value = true

        val currentNickname = nickname.value.orEmpty()
        nobarService.login(LoginParams(currentNickname))
                .onSuccess {
                    authTokenManager.saveNobarToken(NobarAccessToken(it.accessToken))
                    _isSuccess.value = true
                }
                .onFailure {
                    _isSuccess.value = false
                    // 실풰
                }
        _isLoading.value = false
    }
}
