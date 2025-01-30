package com.akash.bank.account

import com.akash.bank.utils.RootError

enum class AccountErrors: RootError {
    UNKNOWN_ERROR,
    FAILED_TO_FETCH,
    ACCOUNT_NOT_FOUND,
}