package org.sopt.appzam.nobar_android.presentation.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityRecipeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class RecipeActivity : BaseActivity<ActivityRecipeBinding>(R.layout.activity_recipe) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getId()
    }

    private fun getId(){
        val recipeId = intent.getStringExtra("recipeId")
    }
}