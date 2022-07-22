package org.sopt.appzam.nobar_android.presentation.onboarding.nickname

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.remote.api.ServiceCreator
import org.sopt.appzam.nobar_android.databinding.ActivityRegisterNicknameBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.HomeActivity

class RegisterNicknameActivity : BaseActivity<ActivityRegisterNicknameBinding>(R.layout.activity_register_nickname) {

    private val registerNicknameViewModel: RegisterNicknameViewModel by viewModels { registerNicknameViewModelFactory }

    private val registerNicknameViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterNicknameViewModel(
                    authTokenManager = AuthTokenManager.getInstance(this@RegisterNicknameActivity),
                    mockupService = ServiceCreator.mockupService,
            ) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = registerNicknameViewModel
        registerNicknameViewModel.isSuccess.observe(this) {
            Intent(applicationContext, HomeActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    .also { startActivity(it); }
        }
    }
}
