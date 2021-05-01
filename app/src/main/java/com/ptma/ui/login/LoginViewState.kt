package com.ptma.ui.login

sealed class LoginViewState

object Default : LoginViewState()

object ValidData : LoginViewState()

object Loading : LoginViewState()

class FaultyData(val invalidUsername: Boolean = false, val invalidPassword: Boolean = false) :
    LoginViewState()

object LoggedIn : LoginViewState()
