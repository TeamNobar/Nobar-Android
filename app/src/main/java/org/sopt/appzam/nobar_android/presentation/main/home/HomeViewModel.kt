package org.sopt.appzam.nobar_android.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.util.enqueueUtil

class HomeViewModel : ViewModel() {
    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private var _laterRecipeList = MutableLiveData<List<RecipeResponse>>()
    val laterRecipeList: LiveData<List<RecipeResponse>> get() = _laterRecipeList

    private var _guideList = MutableLiveData<List<GuideResponse>>()
    val guideList: LiveData<List<GuideResponse>> get() = _guideList

   /* private var _nobarRecipeList = MutableLiveData<List<NobarRecipeResponse>>()
    val nobarRecipeList: LiveData<List<NobarRecipeResponse>> get() = _nobarRecipeList
*/
    fun homeNetwork() {
        val call = ServiceCreator.mockupService.getHomeItem()
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _laterRecipeList.value = it.laterRecipeList
                _guideList.value = it.guideList
            },
            onError = {
                Log.d("status", it.toString())
            }
        )
    }

    //레시피 전체보기 서버통신
    fun homeRecipeDetailNetwork(){
        val call = ServiceCreator.mockupService.getHomeItem()
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _laterRecipeList.value = it.laterRecipeList
            },
            onError = {
                Log.d("status", it.toString())
            }
        )
    }
}