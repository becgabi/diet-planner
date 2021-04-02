package com.ptma.ui.login

import co.zsmb.rainbowcake.withIOContext
import com.ptma.domain.auth.AuthInteractor
import com.ptma.domain.auth.UserCredentials
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    suspend fun login(email: String, password: String) = withIOContext {
        authInteractor.login(UserCredentials(email, password))
    }

}