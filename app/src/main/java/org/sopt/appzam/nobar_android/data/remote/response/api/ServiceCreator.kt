package org.sopt.appzam.nobar_android.data.remote.response.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_MOCKUP = "https://fe667e6b-7b31-4951-9278-de104ecb9c3a.mock.pstmn.io"

    private val retrofit_mockup: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_MOCKUP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mockupService : MockupService = retrofit_mockup.create(MockupService::class.java)
}