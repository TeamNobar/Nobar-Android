package org.sopt.appzam.nobar_android.data.remote.service

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager
import org.sopt.appzam.nobar_android.data.remote.service.interceptor.NobarAuthInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

interface NobarService {

    suspend fun login(): Response<Unit>

    companion object {
        private const val BASE_URL = ""

        @Volatile
        private var instance: NobarService? = null

        @Synchronized
        fun getInstance(context: Context): NobarService {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(
                            OkHttpClient().newBuilder()
                                    .addInterceptor(NobarAuthInterceptor(AuthTokenManager.getInstance(context)))
                                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                                    .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create<NobarService>()
                    .also { this.instance = it }
        }
    }
}
