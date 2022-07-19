package org.sopt.appzam.nobar_android.presentation.view.toast

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes stringRes: Int) = showToast(ToastMessage.ResourceId(stringRes))

fun Context.showToast(message: String) = showToast(ToastMessage.StringValue(message))

fun Context.showToast(toastMessage: ToastMessage) = when (toastMessage) {
    is ToastMessage.ResourceId ->
        Toast.makeText(this, toastMessage.message, Toast.LENGTH_SHORT).show()
    is ToastMessage.StringValue ->
        Toast.makeText(this, toastMessage.message, Toast.LENGTH_SHORT).show()
}
