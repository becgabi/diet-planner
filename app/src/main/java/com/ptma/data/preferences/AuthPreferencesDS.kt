package com.ptma.data.preferences

import com.ptma.data.network.security.JWTParser
import com.ptma.data.network.security.Permission
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthPreferencesDS @Inject constructor(
    private val provider: EncryptedSharedPreferencesProvider,
    private val jwtParser: JWTParser
) {

    val token: String?
        get() {
            val token = provider.getValue(KEY_JWT_TOKEN)
            if (token != null) {
                Timber.i("A token has been found.")
            }
            return token
        }

    val tokenExpiration: LocalDateTime?
        get() = token
            ?.let { jwtParser.parseToken(it) }
            ?.expirationTime

    val permissions: List<Permission>?
        get() = token
            ?.let { token: String -> jwtParser.parseToken(token) }
            ?.permissions

    fun saveToken(token: String?) {
        provider.saveKeyValueEncrypted(KEY_JWT_TOKEN, token)
    }

    fun removeToken() {
        provider.removeKey(KEY_JWT_TOKEN)
    }

    companion object {
        private const val KEY_JWT_TOKEN = "KEY_JWT_TOKEN"
    }
}