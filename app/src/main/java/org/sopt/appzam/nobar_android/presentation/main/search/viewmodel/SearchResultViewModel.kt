package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchResultResponse
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class SearchResultViewModel : ViewModel() {
    private var _searchResult = MutableLiveData<List<RecipeResponse>>()
    val searchResult: LiveData<List<RecipeResponse>> = _searchResult

    fun initSearchResultNetWork(keyword : String){
        val call : Call<SearchResultResponse> = ServiceCreator.mockupService.getSearchResult(keyword)
        call.enqueueUtil(
            onSuccess = { _searchResult.value=it.recipes },
            onError = { Log.d("server", "오류") }
        )
    }
}