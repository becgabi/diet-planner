package com.ptma.data.network.auth

import com.ptma.api.AuthenticationApi
import com.ptma.model.AuthResultDto
import com.ptma.model.UserCredentialsDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNetworkDS @Inject constructor(
    private val authenticationApi: AuthenticationApi
) {

    suspend fun login(email: String, password: String): AuthResultDto? {
        val request = UserCredentialsDto(email, password)
        return authenticationApi.login(request).body()
    }

}