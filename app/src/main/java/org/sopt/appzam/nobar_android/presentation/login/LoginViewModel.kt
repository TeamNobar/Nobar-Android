package org.sopt.appzam.nobar_android.presentation.login

import androidx.lifecycle.*

class LoginViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun isLoginRunning(): Boolean = isLoading.value ?: false

    fun startLoginRunning() {
        _isLoading.value = true
    }

    fun login() {
        // save user auth local
        // save
        _isLoading.value = false
    }
}
