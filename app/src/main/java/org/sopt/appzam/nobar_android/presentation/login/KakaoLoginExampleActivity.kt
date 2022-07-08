package org.sopt.appzam.nobar_android.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import org.sopt.appzam.nobar_android.databinding.ActivityKakaoLoginExampleBinding

class KakaoLoginExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityKakaoLoginExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun loginKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { _, loginError ->
                loginError?.printStackTrace()
                UserApiClient.instance.me { user, error ->

                    error?.printStackTrace()
                }
            }
        }
    }
}
