package org.sopt.appzam.nobar_android.presentation.onboarding

import android.os.Bundle
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityOnBoardingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.pagerOnBoarding.adapter = OnBoardingPageAdapter().apply {
            submitList(OnBoarding.values().toList())
        }
        binding.indicator.setViewPager2(binding.pagerOnBoarding)
    }
}
