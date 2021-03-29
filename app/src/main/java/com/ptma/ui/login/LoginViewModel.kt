package com.ptma.ui.login

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginPresenter: LoginPresenter
) : RainbowCakeViewModel<LoginViewState>(Loading) {

    fun load() = execute {
        viewState = LoginReady(loginPresenter.getData())
    }

}
