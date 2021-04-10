package com.ptma.domain.auth

import com.ptma.data.network.datasources.AuthNetworkDS
import com.ptma.data.network.security.Permission
import com.ptma.data.preferences.AuthPreferencesDS
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authNetworkDS: AuthNetworkDS,
    private val authPreferencesDS: AuthPreferencesDS
) {

    val isUserLoggedIn: Boolean
        get() {
            if (authPreferencesDS.token != null) {
                val tokenValid = authPreferencesDS.tokenExpiration != null
                        && LocalDateTime.now().isBefore(authPreferencesDS.tokenExpiration)
                if (tokenValid) {
                    return true
                }
                authPreferencesDS.removeToken()
            }
            return false
        }

    suspend fun login(email: String, password: String) {
        val loginResult = authNetworkDS.login(email, password)
        if (loginResult != null) {
            authPreferencesDS.saveToken(loginResult.jwtToken)
            Timber.i("Successful login with user $email")
        } else {
            Timber.i("Login has failed with user $email")
        }
    }

    fun logout() {
        authPreferencesDS.removeToken()
    }

    fun hasPermission(permission: Permission?): Boolean {
        return authPreferencesDS.permissions?.contains(permission) ?: false
    }

}