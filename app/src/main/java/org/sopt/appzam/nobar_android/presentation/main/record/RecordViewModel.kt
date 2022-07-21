package org.sopt.appzam.nobar_android.presentation.main.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.presentation.base.NobarViewModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class RecordViewModel() : NobarViewModel() {
    private var _tagList = MutableLiveData<List<TagResponse>>()
    val tagList: LiveData<List<TagResponse>> = _tagList

    fun getTagListNetwork() {
        val call: Call<List<TagResponse>> = ServiceCreator.mockupService.getTagList()
        call.enqueueUtil(
            onSuccess = { _tagList.value=it },
            onError = { Log.d("server", "오류") }
        )
    }
}