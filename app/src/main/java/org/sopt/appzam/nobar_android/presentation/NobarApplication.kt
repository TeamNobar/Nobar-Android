package org.sopt.appzam.nobar_android.presentation

//import com.kakao.sdk.common.KakaoSdk
import android.app.Application
import android.content.Context

class NobarApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        appContext = this
        //KakaoSdk.init(this, getString(R.string.kakaoAppKeyScheme))
    }

    companion object {
        @JvmStatic
        var appContext: Context? = null
    }
}
