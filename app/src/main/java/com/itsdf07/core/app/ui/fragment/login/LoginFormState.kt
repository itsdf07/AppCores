package com.itsdf07.core.app.ui.fragment.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false//disable login button unless both username / password is valid
)