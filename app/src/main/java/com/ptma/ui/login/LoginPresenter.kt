package com.ptma.ui.login

import co.zsmb.rainbowcake.withIOContext
import com.ptma.data.network.security.Permission
import com.ptma.domain.auth.AuthInteractor
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    val isUserLoggedIn: Boolean
        get() = authInteractor.isUserLoggedIn

    suspend fun login(email: String, password: String) = withIOContext {
        authInteractor.login(email, password)
    }

    fun logout() {
        authInteractor.logout()
    }

    fun hasPermission(permission: Permission?): Boolean {
        return authInteractor.hasPermission(permission)
    }

}