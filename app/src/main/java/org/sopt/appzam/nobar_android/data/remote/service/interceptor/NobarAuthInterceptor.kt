package org.sopt.appzam.nobar_android.data.remote.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.appzam.nobar_android.data.local.db.AuthTokenManager

class NobarAuthInterceptor(
        private val nobarTokenManager: AuthTokenManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        /*val nobarToken = nobarTokenManager.getNobarToken()?.accessToken
                ?: return chain.proceed(chain.request())*/

        val nobarToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjJkOWRkYmFkYmI1NDBiODhjZDVlZGM4In0sImlhdCI6MTY1ODQ3NjAwMCwiZXhwIjo0NzgyNjc4NDAwfQ.PDvodnxCJrq8XTxoZIcYdNheK9FSB-wjjfe2_t8-D-Q"
        val tokenAddedRequest = chain.request().newBuilder()
                .addHeader(TOKEN, nobarToken)
                .build()
        return chain.proceed(tokenAddedRequest)
    }

    companion object {
        private const val TOKEN = "token"
    }
}
