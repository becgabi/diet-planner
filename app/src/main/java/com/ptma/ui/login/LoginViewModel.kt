package com.ptma.ui.login

import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.ptma.R
import com.ptma.data.network.security.Permission
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val presenter: LoginPresenter
) : RainbowCakeViewModel<LoginViewState>(Default) {

    object LoginFailedEvent : OneShotEvent

    val isUserLoggedIn: Boolean
        get() = presenter.isUserLoggedIn

    fun login(email: String, password: String) = execute {
        viewState = Loading

        try {
            presenter.login(email, password)
        } catch (e: Exception) {
            Timber.e(e)
            postEvent(LoginFailedEvent)
            return@execute
        }

        viewState = LoggedIn
    }

    fun logout() {
        presenter.logout()
        viewState = Default
    }

    fun hasPermission(permission: Permission?): Boolean {
        return presenter.hasPermission(permission)
    }

    fun loginDataChanged(username: String?, password: String?) {
        viewState = when {
            isEmpty(username) -> FaultyData(invalidUsername = true)
            isEmpty(password) -> FaultyData(invalidPassword = true)
            else -> ValidData
        }
    }

    private fun isEmpty(text: String?): Boolean {
        return text == null || text.trim().isEmpty()
    }

    companion object {
        val menuVisibilities = mapOf(
            R.id.nav_appointment_list to Permission.APPOINTMENT_VIEW,
            R.id.nav_workout_list to Permission.WORKOUT_VIEW
        )
    }
}