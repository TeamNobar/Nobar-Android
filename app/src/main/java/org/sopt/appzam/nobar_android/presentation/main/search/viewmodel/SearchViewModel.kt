package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.response.BaseResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchResultResponse
import org.sopt.appzam.nobar_android.data.remote.response.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class SearchViewModel : ViewModel() {
    private var _baseTagList = MutableLiveData<List<BaseModel>>()
    val baseTagList : LiveData<List<BaseModel>> = _baseTagList

    private var _baseSearchResultList = MutableLiveData<List<RecipeResponse>>()
    val baseSearchResultList : LiveData<List<RecipeResponse>> = _baseSearchResultList

    fun initBaseNetwork() {
        val call: Call<BaseResponse> = ServiceCreator.mockupService.getBaseTag()
        call.enqueueUtil(
            onSuccess = { _baseTagList.value = it.base },
            onError = { Log.d("server", "오류") }
        )
    }

    fun initBaseSearchNetwork(base : String){
        val call: Call<SearchResultResponse> = ServiceCreator.mockupService.getBaseSearch(base)
        call.enqueueUtil(
            onSuccess = { _baseSearchResultList.value = it.recipes },
            onError = { Log.d("server", "오류") }
        )
    }
}