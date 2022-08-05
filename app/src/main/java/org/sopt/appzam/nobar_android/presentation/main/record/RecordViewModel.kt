package org.sopt.appzam.nobar_android.presentation.main.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.params.TastingNoteParams
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.data.remote.response.TastingNoteResponse
import org.sopt.appzam.nobar_android.presentation.base.NobarViewModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class RecordViewModel() : NobarViewModel() {
    //서버에서 받아오는 태그 리스트
    private val _tagList = MutableLiveData<List<TagResponse>>()
    val tagList: LiveData<List<TagResponse>> = _tagList

    //읽기, 수정 시 서버에서 받아오는 테이스팅 노트
    private var _note = MutableLiveData<TastingNoteResponse>()
    val note: LiveData<TastingNoteResponse> = _note

    //쓰기, 수정을 위한 양방향 데이터바인딩
    val cocktailName = MutableLiveData<String>()
    val drinkingDate = MutableLiveData<String>()
    val rating = MutableLiveData<Double>()
    val cocktailEvaluation = MutableLiveData<String>()
    val EvaluationCount = MutableLiveData(0)
    val cocktailTip = MutableLiveData<String>()
    val TipCount = MutableLiveData(0)
    val isTagCountMax = MutableLiveData(false)
    val isTagClicked = MutableLiveData(false)

    //쓰기의 서버통신 완료 여부
    val writingSendComplete = MutableLiveData<Boolean>(false)

    // 쓰기 시 가져올 id
    var cocktailId: String = ""

    // 날짜 보내기 편하기 위해서
    var tmpDate = ""

    var tagCount = 0

    // 쓰기 시 태그 선택하는 로직
    fun setSelectedTag(tagResponse: TagResponse) {
        val size = _tagList.value?.size ?: 0
        tagCount = tagList.value.orEmpty().count { it.isSelected == true }
        isTagCountMax.value = (tagCount >= 3)
        isTagClicked.value = (tagCount > 0)

        for (i in 0..size - 1) {
            if (tagResponse.id == (_tagList.value?.get(i)?.id ?: "")) {
                if (!(_tagList.value?.get(i)?.isSelected ?: false)) {
                    if (isTagCountMax.value ?: false)
                        return
                }
                _tagList.value?.get(i)?.isSelected = !(_tagList.value?.get(i)?.isSelected ?: false)
            }
        }

        tagCount = tagList.value.orEmpty().count { it.isSelected == true }
        isTagCountMax.value = (tagCount >= 3)
        isTagClicked.value = (tagCount > 0)
    }

    // 쓰기 글자 수
    fun updateEvaluationCount() {
        if (cocktailEvaluation.value == null) return
        else EvaluationCount.value = cocktailEvaluation.value?.length ?: 0
    }

    // 쓰기 글자 수
    fun updateTipCount() {
        if (cocktailTip.value == null) return
        else TipCount.value = cocktailTip.value?.length ?: 0
    }

    // 등록 버튼 활성화
    fun canRegister(): Boolean {
        return !cocktailName.value.isNullOrEmpty() and !drinkingDate.value.isNullOrEmpty() and (isTagClicked.value
            ?: false)
    }

    //태그 목록 서버에서 가져오기
    fun getTagListNetwork() {
        val call: Call<List<TagResponse>> = ServiceCreator.mockupService.getTagList()
        call.enqueueUtil(
            onSuccess = { _tagList.value = it },
            onError = { Log.d("server", "오류") }
        )
    }

    //테이스팅 노트 가져오기
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

    fun makeTastingNote(rating: Float): TastingNoteParams {
        val yearRange = IntRange(0, 3)
        val monthRange = IntRange(4, 5)
        val dayRange = IntRange(6, 7)
        val year = tmpDate.slice(yearRange)
        val month = tmpDate.slice(monthRange)
        val day = tmpDate.slice(dayRange)
        val slash = "/"
        val date = year + slash + month + slash + day
        return TastingNoteParams(
            recipeId = cocktailId,
            rate = rating,
            tagList = tagList.value ?: error("태그리스트 없음"),
            tasteContent = cocktailEvaluation.value,
            experienceContent = cocktailTip.value,
            createdAt = date
        )
    }

    //테이스팅 노트 작성 보내기
    fun postTastingNote(tastingNoteParams: TastingNoteParams) {
        val call: Call<TastingNoteResponse> =
            ServiceCreator.mockupService.postNote(tastingNoteParams)
        call.enqueueUtil(
            onSuccess = {
                _note.value = it
                writingSendComplete.value=true
                        },
            onError = { Log.d("server", "오류") }
        )
    }
}