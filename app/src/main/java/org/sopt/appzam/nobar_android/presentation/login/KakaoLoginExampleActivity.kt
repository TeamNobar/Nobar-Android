package org.sopt.appzam.nobar_android.presentation.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import org.sopt.appzam.nobar_android.databinding.ActivityKakaoLoginExampleBinding

class KakaoLoginExampleActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityKakaoLoginExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            loginViewModel.startLoginRunning()
            loginKakao()
        }
        binding.buttonLogout.setOnClickListener { logout() }
        binding.buttonUnlink.setOnClickListener { unlink() }
    }

    private fun loginKakao() {
        if (loginViewModel.isLoginRunning()) return

        if (isKakaoTalkLoginAvailable()) {
            loginWithKakaoTalk()
            return
        }
        loginWithKakaoAccount()
    }

    private fun isKakaoTalkLoginAvailable(): Boolean {
        return UserApiClient.instance.isKakaoTalkLoginAvailable(this)
    }

    private fun loginWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this, callback = ::onLogin)
    }

    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = ::onLogin)
    }

    private fun onLogin(oAuthToken: OAuthToken?, loginError: Throwable?) {
        if (oAuthToken != null) {
            getUserInformation()
            return
        }
        loginError?.printStackTrace()
    }

    private fun getUserInformation() {
        UserApiClient.instance.me { user, error ->
            if (user == null) {
                error?.printStackTrace()
                return@me
            }
            onLoginSuccess(user)
        }
    }

    private fun onLoginSuccess(user: User) {
        val id: Long = user.id
        val nickname: String = user.kakaoAccount?.profile?.nickname ?: error("nickname cannot be null")
        val profileImageUrl: String? = user.kakaoAccount?.profile?.profileImageUrl
        val thumbnailImageUrl: String? = user.kakaoAccount?.profile?.thumbnailImageUrl

        val gender: String? = user.kakaoAccount?.gender?.name
        val age: String? = user.kakaoAccount?.ageRange?.name

        loginViewModel.login()
    }

    private fun logout() {
        UserApiClient.instance.logout {
            if (it == null) {
                Toast.makeText(this, "kakao logout success", Toast.LENGTH_SHORT).show()
                return@logout
            }
            Toast.makeText(this, "kakao logout fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun unlink() {
        UserApiClient.instance.unlink {
            if (it == null) {
                Toast.makeText(this, "kakao unlink success", Toast.LENGTH_SHORT).show()
                return@unlink
            }
            Toast.makeText(this, "kakao unlink fail", Toast.LENGTH_SHORT).show()
        }
    }
}
