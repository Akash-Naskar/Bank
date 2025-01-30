package com.akash.bank.authentication


import com.akash.bank.temporary.LoginStatus
import com.akash.bank.utils.Result

typealias Id = String

interface AuthRepo {
    val id: String?
    suspend fun login(email: String, password: String): Result<String, AuthErrors>
    suspend fun register(email: String, password: String): Result<Id, AuthErrors>
    fun isVerified(): Boolean
    fun isLoggedIn(): LoginStatus
}