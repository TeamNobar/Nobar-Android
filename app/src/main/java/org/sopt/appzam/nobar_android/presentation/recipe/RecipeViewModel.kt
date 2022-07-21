package org.sopt.appzam.nobar_android.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.data.remote.response.RecipeDetailResponse
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.data.remote.response.common.GlassModel
import org.sopt.appzam.nobar_android.data.remote.response.common.RecipeCoreInfo
import org.sopt.appzam.nobar_android.data.remote.response.common.SkillModel
import org.sopt.appzam.nobar_android.presentation.base.NobarViewModel
import org.sopt.appzam.nobar_android.util.enqueueUtil
import retrofit2.Call

class RecipeViewModel : NobarViewModel() {
    private var _recipeDetail = MutableLiveData<RecipeDetailResponse>()
    val recipeDetail: LiveData<RecipeDetailResponse> = _recipeDetail

    private var _recipeCoreInfo = MutableLiveData<List<RecipeCoreInfo>>()
    val recipeCoreInfo: LiveData<List<RecipeCoreInfo>> = _recipeCoreInfo

    private var _stepSize = MutableLiveData<Int>()
    val stepSize: LiveData<Int> = _stepSize

    var scrap: Boolean = false

    fun initStepSize(size: Int) {
        _stepSize.value = size
    }

    fun initRecipeDetail(recipId: String) {
        val call: Call<RecipeDetailResponse> = ServiceCreator.mockupService.getRecipeDetail(recipId)
        call.enqueueUtil(
            onSuccess = {
                _recipeDetail.value = it
                _recipeCoreInfo.value = mapData(it.base, it.proof, it.proofIcon, it.skill, it.glass)
            },
            onError = { Log.d("server", "오류") }
        )
    }

    fun patchScrap(recipeId: String, scrap: Boolean) {
        val call: Call<RecipeDetailResponse> =
            ServiceCreator.mockupService.patchScrap(recipeId, scrap)
        call.enqueueUtil(
            onSuccess = {
                _recipeDetail.value = it
                _recipeCoreInfo.value = mapData(it.base, it.proof, it.proofIcon, it.skill, it.glass)
            },
            onError = { Log.d("server", "오류") }
        )
    }

    private fun mapData(
        base: BaseModel,
        proof: Int,
        proofIcon: String,
        skill: SkillModel,
        glass: GlassModel
    ): ArrayList<RecipeCoreInfo> {
        val list = ArrayList<RecipeCoreInfo>()
        list.add(RecipeCoreInfo(base.url, "베이스", base.name))
        list.add(RecipeCoreInfo(proofIcon, "도수", proof.toString() + "도"))
        list.add(RecipeCoreInfo(skill.url, "만드는법", skill.name))
        list.add(RecipeCoreInfo(glass.url, "잔", glass.name))

        return list
    }
}