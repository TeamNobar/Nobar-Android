package org.sopt.appzam.nobar_android.presentation.main.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchDetailViewModel : ViewModel() {
    var searchingWord = MutableLiveData<String>()

    fun check(): Boolean {
        if (searchingWord.value.isNullOrBlank()) {
            Log.d("asdf", "비어있다.")
            return false
        } else {
            Log.d("asdf", "안비어있다.")
            return true
        }
    }
}