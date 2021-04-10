package com.ptma.data.network.interceptor

import com.ptma.data.preferences.AuthPreferencesDS
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtAuthInterceptor @Inject constructor(
    private val authPreferencesDS: AuthPreferencesDS
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getAuthorizedRequest(chain)
        return chain.proceed(request)
    }

    private fun getAuthorizedRequest(chain: Interceptor.Chain): Request {
        return Optional.ofNullable(authPreferencesDS.token)
            .map { token: String? ->
                chain.request()
                    .newBuilder()
                    .header("Authorization", String.format("Bearer %s", token))
                    .build()
            }
            .orElse(chain.request())
    }
}