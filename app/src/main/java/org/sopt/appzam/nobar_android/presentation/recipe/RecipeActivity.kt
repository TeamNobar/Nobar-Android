package org.sopt.appzam.nobar_android.presentation.recipe

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityRecipeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class RecipeActivity : BaseActivity<ActivityRecipeBinding>(R.layout.activity_recipe) {
    private val recipeViewModel : RecipeViewModel by viewModels()
    private lateinit var recipeCoreInfoAdapter : RecipeCoreInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getRecipeDetailData()
        initRecipeCoreInfoAdapter()
        observingCoreData()
    }

    private fun initRecipeCoreInfoAdapter(){
        recipeCoreInfoAdapter = RecipeCoreInfoAdapter()
        binding.recyclerCoreInfo.adapter=recipeCoreInfoAdapter
    }

    private fun observingCoreData(){
        recipeViewModel.recipeCoreInfo.observe(this){
            recipeCoreInfoAdapter.submitList(it)
        }
    }

    private fun getRecipeDetailData(){
        val cocktailId = intent.getStringExtra("cocktailId") ?: ""
        recipeViewModel.initRecipeDetail(cocktailId)
    }
}