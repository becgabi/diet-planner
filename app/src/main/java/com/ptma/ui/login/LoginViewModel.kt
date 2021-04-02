package com.ptma.ui.login

import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val presenter: LoginPresenter
) : RainbowCakeViewModel<LoginViewState>(Default) {

    object LoginFailedEvent : OneShotEvent

    fun login(email: String, password: String) = execute {
        viewState = Loading

        try {
            presenter.login(email, password)
        } catch (e: Exception) {
            postEvent(LoginFailedEvent)
            return@execute
        }

        viewState = LoggedIn
    }

}
