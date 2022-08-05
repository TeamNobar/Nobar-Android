package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.TastingNoteResponse
import org.sopt.appzam.nobar_android.util.enqueueUtil

class MyPageViewModel : ViewModel() {

    //서버통신이 완료되었는지를 확인
    val getListComplete = MutableLiveData(false)

    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private var _laterRecipes = MutableLiveData<List<RecipeResponse>>()
    val laterRecipes: LiveData<List<RecipeResponse>> get() = _laterRecipes

    private var _tastingNotes = MutableLiveData<List<TastingNoteResponse>>()
    val tastingNotes: LiveData<List<TastingNoteResponse>> get() = _tastingNotes


    fun myPageNetwork() {
        val call = ServiceCreator.mockupService.getMyPageItem()
        call.enqueueUtil(
            onSuccess = {
                _state.value = true
                _laterRecipes.value = it.laterRecipes
                _tastingNotes.value = it.tastingNotes
                getListComplete.value=true
            },
            onError = {
                Log.d("status", it.toString())
            }
        )
    }
}