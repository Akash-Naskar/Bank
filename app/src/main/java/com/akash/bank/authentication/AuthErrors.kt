package com.akash.bank.authentication

import com.akash.bank.utils.RootError

enum class AuthErrors: RootError {
    UNKNOWN_ERROR,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_NAME,
    EMAIL_ALREADY_IN_USE,
    WEAK_PASSWORD,
    USER_DISABLED,
    USER_NOT_FOUND,
    WRONG_PASSWORD,
    NETWORK_ERROR,
    ACCOUNT_NOT_FOUND,
    INVALID_CREDENTIALS
}