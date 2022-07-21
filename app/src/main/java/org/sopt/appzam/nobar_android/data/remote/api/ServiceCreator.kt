package org.sopt.appzam.nobar_android.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_MOCKUP = "https://fe667e6b-7b31-4951-9278-de104ecb9c3a.mock.pstmn.io"

    private val retrofitMockup: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_MOCKUP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mockupService: MockupService = retrofitMockup.create(MockupService::class.java)
}