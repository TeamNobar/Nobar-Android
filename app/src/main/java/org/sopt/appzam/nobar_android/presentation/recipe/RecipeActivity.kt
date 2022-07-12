package org.sopt.appzam.nobar_android.presentation.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.appzam.nobar_android.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}