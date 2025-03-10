package com.akash.bank.temporary

import android.content.Context
import com.akash.bank.authentication.AuthErrors
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.authentication.Id
import com.akash.bank.utils.Result

data class TemporaryAuthWrapper(
    val id: String,
    val email: String,
    val password: String,
    val isVerified: Boolean = false
)


class TemporaryAuthRepo(
    private val context: Context
) : AuthRepo {
    override val id: String? = null
    private var accounts = mutableListOf<TemporaryAuthWrapper>()

    init {
        accounts.add(TemporaryAuthWrapper("2", "", ""))
        accounts.add(TemporaryAuthWrapper("1", "q", "q"))
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Id, AuthErrors> {
        val account = accounts.find { it.email == email && it.password == password }
        if (account == null) {
            return Result.Error(AuthErrors.USER_NOT_FOUND)
        }
        return Result.Success(account.id)
    }

    private fun getRandomString(length: Int = 10): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<Id, AuthErrors> {
        val id = getRandomString()
        accounts.add(TemporaryAuthWrapper(id, email, password))

        return Result.Success(id)
    }

    override fun isVerified(): Boolean = true

    override fun isLoggedIn(): LoginStatus = if (id != null) {
        LoginStatus.HasLoggedIn(id)
    } else {
        LoginStatus.HasNotLoggedIn
    }
//    override fun isLoggedIn(): LoginStatus = if (id != null) {
//        LoginStatus.HasLoggedIn(id)
//    } else {
//        LoginStatus.HasNotLoggedIn
//    }
}

sealed interface LoginStatus {
    data object HasNotLoggedIn : LoginStatus
    data class HasLoggedIn(val id: String) : LoginStatus
}