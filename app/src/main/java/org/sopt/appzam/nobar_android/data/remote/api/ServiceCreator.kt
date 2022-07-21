package org.sopt.appzam.nobar_android.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_MOCKUP = "http://13.124.241.36:8000/"

    private val retrofitMockup: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_MOCKUP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mockupService: MockupService = retrofitMockup.create(MockupService::class.java)
}