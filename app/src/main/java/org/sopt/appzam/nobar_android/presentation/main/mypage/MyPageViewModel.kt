package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.MyPageLaterRecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.MyPageTastingResponse
import org.sopt.appzam.nobar_android.util.enqueueUtil

class MyPageViewModel : ViewModel() {
    private val call = ServiceCreator.mockupService.getMyPageItem()

    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private var _laterRecipes = MutableLiveData<List<MyPageLaterRecipeResponse>>()
    val laterRecipes : LiveData<List<MyPageLaterRecipeResponse>> get() = _laterRecipes

    private var _tastingNotes = MutableLiveData<List<MyPageTastingResponse>>()
    val tastingNotes : LiveData<List<MyPageTastingResponse>> get() = _tastingNotes


    fun myPageNetwork() {
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _laterRecipes.value = it.laterRecipes
                _tastingNotes.value = it.tastingNotes
            },
            onError = {
                Log.d("status", it.toString())
            }
        )
    }
}