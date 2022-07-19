package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchDetailViewModel : ViewModel() {
    var searchingWord = MutableLiveData<String>()
    var visibility = MutableLiveData<Boolean>(false)

}