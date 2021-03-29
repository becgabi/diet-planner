package com.ptma.ui.login

sealed class LoginViewState

object Loading : LoginViewState()

data class LoginReady(val data: String = "") : LoginViewState()
