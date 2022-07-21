package org.sopt.appzam.nobar_android.presentation.main.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivitySettingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingButton()

    }

    private fun settingButton(){
        binding.imageBack.setOnClickListener {
            finish()
        }
    }
}