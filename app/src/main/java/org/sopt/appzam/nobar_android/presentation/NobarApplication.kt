package org.sopt.appzam.nobar_android.presentation

import android.app.Application
//import com.kakao.sdk.common.KakaoSdk
import org.sopt.appzam.nobar_android.R

class NobarApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        //KakaoSdk.init(this, getString(R.string.kakaoAppKeyScheme))
    }
}
