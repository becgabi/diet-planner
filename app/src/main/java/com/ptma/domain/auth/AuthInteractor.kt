package com.ptma.domain.auth

import com.ptma.data.network.auth.AuthNetworkDS
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authNetworkDS: AuthNetworkDS
) {

    suspend fun login(request: UserCredentials) {
        authNetworkDS.login(request)
    }

}