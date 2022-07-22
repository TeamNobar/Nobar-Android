package org.sopt.appzam.nobar_android.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.presentation.main.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, SPLASH_TIME)
        setContentView(R.layout.activity_splash)
    }

    companion object {
        const val SPLASH_TIME = 2000L
    }

}