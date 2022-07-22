package org.sopt.appzam.nobar_android.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.remote.service.interceptor.NobarAuthInterceptor
import org.sopt.appzam.nobar_android.presentation.NobarApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_MOCKUP = "http://13.124.241.36:8000"

    private val retrofitMockup: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_MOCKUP)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                    OkHttpClient().newBuilder()
                            .addInterceptor(NobarAuthInterceptor(AuthTokenManager.getInstance(NobarApplication.appContext!!)))
                            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                            .build()
            )
            .build()


    val mockupService: MockupService = retrofitMockup.create(MockupService::class.java)
}
