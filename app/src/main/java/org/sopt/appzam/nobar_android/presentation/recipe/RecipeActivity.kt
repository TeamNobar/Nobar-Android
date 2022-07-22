package org.sopt.appzam.nobar_android.presentation.recipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityRecipeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.MODE
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.NOTE_ID
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.NOTE_NAME
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.RECIPE

class RecipeActivity : BaseActivity<ActivityRecipeBinding>(R.layout.activity_recipe) {
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recipeCoreInfoAdapter: RecipeCoreInfoAdapter
    private lateinit var recipeIngredientAdapter: RecipeIngredientAdapter
    private lateinit var recipeStepAdapter: RecipeStepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getRecipeDetailData()

        initRecipeCoreInfoAdapter()
        initRecipeIngredientAdapter()
        initRecipeStepAdapter()

        initRecipeData()
        initCoreData()
        initIngredientData()
        initStepData()
        clickHeart()
        clickBack()
        clickWritingNote()
    }

    private fun initRecipeCoreInfoAdapter() {
        recipeCoreInfoAdapter = RecipeCoreInfoAdapter()
        binding.recyclerCoreInfo.adapter = recipeCoreInfoAdapter
    }

    private fun initRecipeIngredientAdapter() {
        recipeIngredientAdapter = RecipeIngredientAdapter()
        binding.recyclerIngredients.adapter = recipeIngredientAdapter
    }

    private fun initRecipeStepAdapter() {
        recipeStepAdapter = RecipeStepAdapter({ decideColor() }, this)
        binding.recyclerSteps.adapter = recipeStepAdapter
    }

    private fun clickBack() {
        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private fun clickHeart() {
        binding.imageBookmark.setOnClickListener {
            val scrap = !recipeViewModel.scrap
            Log.d("asdf", "viewmodel scrap : ${recipeViewModel.scrap}")
            Log.d("asdf", "scrap : $scrap")
            decideHeart(scrap)
            recipeViewModel.patchScrap(recipeViewModel.recipeDetail.value?.id ?: "", scrap)
        }
    }

    private fun clickWritingNote() {
        binding.buttonWriteTatstingNote.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            intent.putExtra(MODE, RECIPE)
            intent.putExtra(NOTE_NAME, recipeViewModel.recipeDetail.value?.name ?: "")
            intent.putExtra(NOTE_ID, recipeViewModel.recipeDetail.value?.id ?: "")
            startActivity(intent)
        }
    }

    private fun decideHeart(scrap: Boolean) {
        when (scrap) {
            true -> binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_full)
            false -> binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_empty)
        }
    }

    private fun initRecipeData() {
        recipeViewModel.recipeDetail.observe(this) {
            binding.model = it
            decideHeart(it.isScrap)
            recipeViewModel.scrap = it.isScrap
        }
    }

    private fun initCoreData() {
        recipeViewModel.recipeCoreInfo.observe(this) {
            recipeCoreInfoAdapter.submitList(it)
        }
    }

    private fun initIngredientData() {
        recipeViewModel.recipeDetail.observe(this) {
            recipeIngredientAdapter.submitList(it.ingredients)
        }
    }

    private fun initStepData() {
        recipeViewModel.recipeDetail.observe(this) {
            recipeStepAdapter.submitList(it.steps)
            recipeViewModel.initStepSize(it.steps.size)
        }
    }

    private fun decideColor(): ArrayList<Int> {
        var size: Int = -1
        recipeViewModel.stepSize.observe(this) {
            size = it
        }
        Log.d("asdf", "colorlist size : $size")

        val colorList = ArrayList<Int>()
        when (size) {
            3 -> {
                colorList.addAll(listOf(R.color.step20, R.color.step60, R.color.step100))
            }
            4 -> {
                colorList.addAll(
                    listOf(
                        R.color.step20,
                        R.color.step45,
                        R.color.step75,
                        R.color.step100
                    )
                )
            }
            5 -> {
                colorList.addAll(
                    listOf(
                        R.color.step20,
                        R.color.step40,
                        R.color.step60,
                        R.color.step80,
                        R.color.step100
                    )
                )
            }
            else -> error("스텝의 개수가 이상합니다. steps : $size")
        }
        return colorList
    }

    private fun getRecipeDetailData() {
        val cocktailId = intent.getStringExtra("cocktailId") ?: ""
        recipeViewModel.initRecipeDetail(cocktailId)
    }
}