package org.sopt.appzam.nobar_android.presentation.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityGuideDetailBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class GuideDetailActivity : BaseActivity<ActivityGuideDetailBinding>(R.layout.activity_guide_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imageBack.setOnClickListener {
            finish()
        }
    }
}