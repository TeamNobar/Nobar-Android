package org.sopt.appzam.nobar_android.presentation.main.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.data.remote.response.TastingNoteResponse
import org.sopt.appzam.nobar_android.presentation.base.NobarViewModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class RecordViewModel() : NobarViewModel() {
    private val _tagList = MutableLiveData<List<TagResponse>>()
    val tagList: LiveData<List<TagResponse>> = _tagList

    private var _note = MutableLiveData<TastingNoteResponse>()
    val note: LiveData<TastingNoteResponse> = _note

    val cocktailName = MutableLiveData<String>()
    val drinkingDate = MutableLiveData<String>()
    val rating = MutableLiveData<Double>()
    val cocktailEvaluation = MutableLiveData<String>()
    val EvaluationCount = MutableLiveData(0)
    val cocktailTip = MutableLiveData<String>()
    val TipCount = MutableLiveData(0)
    val isTagCountMax = MutableLiveData(false)

    var tagCount = 0

    fun setSelectedTag(tagResponse: TagResponse) {
        val size = _tagList.value?.size ?: 0
        tagCount = tagList.value.orEmpty().count { it.isSelected == true }
        isTagCountMax.value = (tagCount >= 3)

        for (i in 0..size - 1) {
            if (tagResponse.id == _tagList.value?.get(i)?.id ?: "") {
                if (!(_tagList.value?.get(i)?.isSelected ?: false)) {
                    if (isTagCountMax.value ?: false)
                        return
                }
                _tagList.value?.get(i)?.isSelected = !(_tagList.value?.get(i)?.isSelected ?: false)
            }
        }

        tagCount = tagList.value.orEmpty().count { it.isSelected == true }
        isTagCountMax.value = (tagCount >= 3)
    }

    fun updateEvaluationCount() {
        if (cocktailEvaluation.value == null) return
        else EvaluationCount.value = cocktailEvaluation.value?.length ?: 0
    }

    fun updateTipCount() {
        if (cocktailTip.value == null) return
        else TipCount.value = cocktailTip.value?.length ?: 0
    }

    fun getTagListNetwork() {
        val call: Call<List<TagResponse>> = ServiceCreator.mockupService.getTagList()
        call.enqueueUtil(
            onSuccess = { _tagList.value = it },
            onError = { Log.d("server", "오류") }
        )
    }

    fun getTastingNote(id: String) {
        val call: Call<TastingNoteResponse> = ServiceCreator.mockupService.getNote(id)
        call.enqueueUtil(
            onSuccess = {
                _note.value = it
                cocktailName.value = it.recipe.name
                drinkingDate.value = it.createdAt
                _tagList.value = it.tag
                tagCount = it.tag.size
                rating.value = it.rate
                cocktailEvaluation.value = it.tasteContent
                cocktailTip.value = it.experienceContent
            },
            onError = { Log.d("server", "오류") }
        )
    }
}