package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.local.db.SearchSharedPreferences
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchKeywordsResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchResultResponse
import org.sopt.appzam.nobar_android.data.remote.response.common.RecommendModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class SearchDetailViewModel : ViewModel() {
    var searchingWord = MutableLiveData<String>()
    var resultAndXVisibility = MutableLiveData<Boolean>(false)
    var recentVisibility = MutableLiveData<Boolean>(false)
    var shareKeyword: String = ""
    var from = ""

    val time = SimpleDateFormat("yyyy.MM.dd 기준").format(Date())

    private var _recentKeyword = MutableLiveData<String>()
    val recentKeyword: LiveData<String> = _recentKeyword

    private var _searchKeyRecommends = MutableLiveData<List<RecommendModel>>()
    val searchKeyRecommends: LiveData<List<RecommendModel>> = _searchKeyRecommends

    private var _searchKeyIngredients = MutableLiveData<List<IngredientResponse>>()
    val searchKeyIngredients: LiveData<List<IngredientResponse>> = _searchKeyIngredients

    private var _searchKeyRecipe = MutableLiveData<List<RecipeResponse>>()
    val searchKeyRecipe: LiveData<List<RecipeResponse>> = _searchKeyRecipe

    private var _searchResult = MutableLiveData<List<RecipeResponse>>()
    val searchResult: LiveData<List<RecipeResponse>> = _searchResult

    fun modifyRecentKeyword(keyword: String) {
        _recentKeyword.value = keyword
    }

    fun initSearchDetailNetwork() {
        val call: Call<SearchKeywordsResponse> = ServiceCreator.mockupService.getSearchKeywords()
        call.enqueueUtil(
            onSuccess = {
                _searchKeyRecommends.value = it.recommends
                _searchKeyIngredients.value = it.ingredients
                _searchKeyRecipe.value = it.recipes
            },
            onError = { Log.d("server", "오류") }
        )
    }

    fun initSearchResultNetWork(keyword: String) {
        val call: Call<SearchResultResponse> = ServiceCreator.mockupService.getSearchResult(keyword)
        shareKeyword = keyword
        call.enqueueUtil(
            onSuccess = { _searchResult.value = it.recipes },
            onError = { Log.d("server", "오류") }
        )
    }

    fun add2Local(context: Context, editText: EditText) {
        SearchSharedPreferences.init(context, RECENT)
        SearchSharedPreferences.addString2Pref(
            RECENT,
            editText.text.toString()
        )
    }

    fun loadfromLocal(context: Context): ArrayList<String> {
        SearchSharedPreferences.init(context, RECENT)
        val list = SearchSharedPreferences.getStringArrayPref(RECENT)
        recentVisibility.value = !list.isEmpty()
        Log.d("asdf", "boolean value : ${!list.isEmpty()}")
        return list
    }

    fun deleteLocal(context: Context) {
        SearchSharedPreferences.init(context, RECENT)
        SearchSharedPreferences.clearPref(RECENT)
    }


    companion object {
        private const val RECENT = "RECENT"
    }
}