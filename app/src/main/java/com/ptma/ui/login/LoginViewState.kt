package com.ptma.ui.login

sealed class LoginViewState

object Default : LoginViewState()

object Loading : LoginViewState()

object LoggedIn : LoginViewState()

object LoginFailed : LoginViewState()