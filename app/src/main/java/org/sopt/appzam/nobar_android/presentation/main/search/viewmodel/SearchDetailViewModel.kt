package org.sopt.appzam.nobar_android.presentation.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.SearchKeywordsResponse
import org.sopt.appzam.nobar_android.data.remote.response.common.RecommendModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class SearchDetailViewModel : ViewModel() {
    var searchingWord = MutableLiveData<String>()
    var visibility = MutableLiveData<Boolean>(false)

    private var _searchKeyRecommends = MutableLiveData<List<RecommendModel>>()
    val searchKeyRecommends: LiveData<List<RecommendModel>> = _searchKeyRecommends

    private var _searchKeyIngredients = MutableLiveData<List<IngredientResponse>>()
    val searchKeyIngredients: LiveData<List<IngredientResponse>> = _searchKeyIngredients

    private var _searchKeyRecipe = MutableLiveData<List<RecipeResponse>>()
    val searchKeyRecipe: LiveData<List<RecipeResponse>> = _searchKeyRecipe

    /*fun parseData(key: String): ArrayList<String> {
        when (key) {
            INGREDIENTS -> {
                val tmpModel = searchKeyIngredients.value
                val tmpList = ArrayList<String>()
                for (i in 0..tmpModel!!.size) {
                    tmpList.add(tmpModel.get(i).name)
                }
                return tmpList
            }
            RECIPES -> {
                val tmpModel = searchKeyRecipe.value
                val tmpList = ArrayList<String>()
                for (i in 0..tmpModel!!.size) {
                    tmpList.add(tmpModel.get(i).name)
                }
                return tmpList
            }
            else->{
                error("0,1,2가 아닌 잘못된 key값이 넘어왔습니다. key : $key")}
        }
    }*/

    /*fun initLocal(context: Context, key : String) {
        SearchSharedPreferences.init(context, key)
        SearchSharedPreferences.setStringArrayPref(context, key, parseData(key))
    }

    fun getFromLocal(context : Context, key : String){
        SearchSharedPreferences.init(context, key)
        SearchSharedPreferences.getStringArrayPref(context, key)
    }*/

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

    companion object {
        private const val RECIPES = "RECIPES"
        private const val INGREDIENTS = "INGREDIENTS"
    }
}