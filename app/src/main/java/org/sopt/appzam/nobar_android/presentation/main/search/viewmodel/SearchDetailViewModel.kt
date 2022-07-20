package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.SearchKeywordsResponse
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class SearchDetailViewModel : ViewModel() {
    var searchingWord = MutableLiveData<String>()
    var visibility = MutableLiveData<Boolean>(false)

    private var _searchKeywords = MutableLiveData<SearchKeywordsResponse>()
    val searchKeywords : LiveData<SearchKeywordsResponse> = _searchKeywords

    fun initSearchDetailNetwork(){
        val call: Call<SearchKeywordsResponse> = ServiceCreator.mockupService.getSearchKeywords()
        call.enqueueUtil(
            onSuccess = { _searchKeywords.value = it },
            onError = { Log.d("server", "오류") }
        )
    }
}