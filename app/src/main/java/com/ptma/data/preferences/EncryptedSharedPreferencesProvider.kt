package com.ptma.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import timber.log.Timber
import java.io.IOException
import java.security.GeneralSecurityException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptedSharedPreferencesProvider @Inject constructor(
    private val context: Context
) {

    fun saveKeyValueEncrypted(key: String?, value: String?) {
        getSharedPreferences()?.edit { putString(key, value) }
    }

    fun getValue(key: String?): String? {
        return getSharedPreferences()?.getString(key, null)
    }

    fun removeKey(key: String?) {
        getSharedPreferences()?.edit { remove(key) }
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return try {
            EncryptedSharedPreferences.create(
                context,
                SECRET,
                getMasterKey(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (exception: GeneralSecurityException) {
            Timber.e("Error occurred during loading the encrypted shared preferences")
            null
        } catch (exception: IOException) {
            Timber.e("Error occurred during loading the encrypted shared preferences")
            null
        }
    }

    @Throws(GeneralSecurityException::class, IOException::class)
    private fun getMasterKey(): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    companion object {
        private const val SECRET = "secret"
    }
}