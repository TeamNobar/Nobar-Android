package org.sopt.appzam.nobar_android.presentation.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityHomeLaterReipeDetailBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter

class HomeLaterRecipeDetailActivity : BaseActivity<ActivityHomeLaterReipeDetailBinding>(R.layout.activity_home_later_reipe_detail) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var laterRecipeDetailAdapter : SearchResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_later_reipe_detail)
        binding.laterRecipeDetailViewModel = homeViewModel
    }
}