package org.sopt.appzam.nobar_android.presentation.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthError
import com.kakao.sdk.common.model.AuthErrorCause
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
            loginKakao()
        }
        binding.buttonLogout.setOnClickListener { logout() }
        binding.buttonUnlink.setOnClickListener { unlink() }
        binding.buttonMe.setOnClickListener {
            UserApiClient.instance.me { user, error ->
                if (user == null) {
                    error?.printStackTrace()
                    return@me
                }
                Log.d("MalibinDebug", "user : $user")
            }
        }

        UserApiClient.instance
    }

    private fun loginKakao() {
        //if (loginViewModel.isLoginRunning())
            //return loginViewModel.startLoginRunning()

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
            getUserInformation(oAuthToken)
            return
        }
        if (loginError is AuthError) {
            if (loginError.reason == AuthErrorCause.AccessDenied) onLoginCanceled()
        }
        loginError?.printStackTrace()
    }

    private fun getUserInformation(oAuthToken: OAuthToken) {
        UserApiClient.instance.me { user, error ->
            if (user == null) {
                error?.printStackTrace()
                return@me
            }
            onLoginSuccess(oAuthToken, user)
        }
    }

    private fun onLoginSuccess(oAuthToken: OAuthToken, user: User) {
        val id: Long = user.id
        val nickname: String = user.kakaoAccount?.profile?.nickname ?: error("nickname cannot be null")
        val profileImageUrl: String? = user.kakaoAccount?.profile?.profileImageUrl
        val thumbnailImageUrl: String? = user.kakaoAccount?.profile?.thumbnailImageUrl
        user.hasSignedUp

       loginViewModel.login()
    }

    private fun onLoginCanceled() {
        Log.d("MalibinDebug", "loginCanceled")
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
