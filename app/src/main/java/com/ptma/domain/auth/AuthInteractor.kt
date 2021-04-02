package com.ptma.domain.auth

import com.ptma.data.network.auth.AuthDataSource
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authDataSource: AuthDataSource
) {

    suspend fun login(request: UserCredentials) {
        authDataSource.login(request)
    }

}