package org.sopt.appzam.nobar_android.presentation.base

import androidx.lifecycle.*
import org.sopt.appzam.nobar_android.presentation.view.toast.ToastMessage

open class NobarViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _toastMessage = MutableLiveData<ToastMessage>()
    val toastMessage: LiveData<ToastMessage> = _toastMessage

    protected fun startLoading() {
        _isLoading.value = true
    }

    protected fun stopLoading() {
        _isLoading.value = false
    }

    protected fun notifySuccess() {
        _isSuccess.value = true
    }

    protected fun showToast(message: String) {
        showToast(ToastMessage.StringValue(message))
    }

    protected fun showToast(toastMessage: ToastMessage) {
        _toastMessage.value = toastMessage
    }
}
