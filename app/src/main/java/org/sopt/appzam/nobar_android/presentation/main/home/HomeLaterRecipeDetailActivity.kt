package org.sopt.appzam.nobar_android.presentation.main.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityHomeLaterReipeDetailBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class HomeLaterRecipeDetailActivity :
    BaseActivity<ActivityHomeLaterReipeDetailBinding>(R.layout.activity_home_later_reipe_detail) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var laterRecipeDetailAdapter: LaterRecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.laterRecipeDetailViewModel = homeViewModel

        laterRecipeDetailAdapter()
        homeDetailNetwork()
        laterRecipeDetailObserver()
        clickButton()
    }

    private fun homeDetailNetwork() {
        homeViewModel.homeRecipeDetailNetwork()
    }

    private fun laterRecipeDetailAdapter() {
        laterRecipeDetailAdapter = LaterRecipeAdapter {
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("recipeId", it.id)
            startActivity(intent)
        }
        binding.recyclerLaterRecipe.adapter = laterRecipeDetailAdapter
    }

    private fun laterRecipeDetailObserver() {
        homeViewModel.laterRecipeList.observe(this){
            laterRecipeDetailAdapter.submitList(it)
        }
    }

    private fun clickButton(){
        binding.imageX.setOnClickListener {
            finish()
        }
    }


}