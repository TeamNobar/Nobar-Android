package org.sopt.appzam.nobar_android.presentation.view.toast

import androidx.annotation.StringRes

sealed class ToastMessage {
    data class ResourceId(@StringRes val message: Int) : ToastMessage()
    data class StringValue(val message: String) : ToastMessage()
}
