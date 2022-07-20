package org.sopt.appzam.nobar_android.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.api.MockupService
import org.sopt.appzam.nobar_android.data.remote.response.api.ServiceCreator
import org.sopt.appzam.nobar_android.util.enqueueUtil

class HomeViewModel : ViewModel() {
    private val call = ServiceCreator.mockupService.getHomeItem()

    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private var _laterRecipeList = MutableLiveData<List<RecipeResponse>>()
    val laterRecipeList : LiveData<List<RecipeResponse>> get() = _laterRecipeList

    private var _guideList = MutableLiveData<List<GuideResponse>>()
    val guideList : LiveData<List<GuideResponse>> get() = _guideList

    private var _nobarRecipeList = MutableLiveData<List<NobarRecipeResponse>>()
    val nobarRecipeList : LiveData<List<NobarRecipeResponse>> get() = _nobarRecipeList

    fun homeNetwork(){
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _laterRecipeList.value = it.laterRecipeList
                _guideList.value = it.guideList
                _nobarRecipeList.value = it.noBarRecipes
            },
            onError = {
                Log.d("status",it.toString())
            }
        )
    }
/*
    fun guideNetwork(){
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _guideList.value = it.guideList
            },
            onError = {
                Log.d("status",it.toString())
            }
        )
    }

    fun nobarRecipeNetwork(){
        call.enqueueUtil(
            onSuccess = {
                _state.value = true

            },
            onError = {
                Log.d("status",it.toString())
            }
        )
    }*/
}