package com.ptma.data.network.auth

import com.ptma.domain.auth.UserCredentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataSource @Inject constructor() {

    suspend fun login(request: UserCredentials) {
        TODO("Call API")
    }

}