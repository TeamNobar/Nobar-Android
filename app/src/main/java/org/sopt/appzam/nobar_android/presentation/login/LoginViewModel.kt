package org.sopt.appzam.nobar_android.presentation.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.local.entity.KakaoUser
import org.sopt.appzam.nobar_android.data.remote.service.NobarService
import org.sopt.appzam.nobar_android.data.remote.utils.onFailure
import org.sopt.appzam.nobar_android.data.remote.utils.onSuccess

class LoginViewModel(
        private val authTokenManager: AuthTokenManager,
        private val nobarService: NobarService,
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun isLoginRunning(): Boolean = isLoading.value ?: false

    fun startLoginRunning() {
        _isLoading.value = true
    }

    fun login(kakaoUser: KakaoUser) = viewModelScope.launch {
        _isLoading.value = true


        // save user auth local
        // save
        val response = nobarService.login()
                .onSuccess { }
                .onFailure { }

        _isLoading.value = false
    }
}
