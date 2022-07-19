package org.sopt.appzam.nobar_android.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.remote.service.NobarService
import org.sopt.appzam.nobar_android.databinding.ActivityLoginBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels { loginViewModelFactory }

    private val loginViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(
                    authTokenManager = AuthTokenManager.getInstance(this@LoginActivity),
                    nobarService = NobarService.getInstance(this@LoginActivity),
            ) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = loginViewModel

        loginViewModel.isSuccess.observe(this) {
            if (it) {
                // login Success
            } else {
                // login Fail
            }
        }
    }
}
