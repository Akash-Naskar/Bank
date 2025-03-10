package com.akash.bank.local

import com.akash.bank.account.AccountInfo
import com.akash.bank.account.AccountRepo
import com.akash.bank.utils.Result
import com.akash.bank.utils.Result.*
import org.koin.compose.koinInject

object LocalResources {
    private var accountInfo: AccountInfo? = null
    private var id: String? = null
    fun setId(id: String) {
        this.id = id
    }
    fun getId(): String {
        if (id == null) {
            throw RuntimeException("Id not set")
        }
        return id!!
    }
    suspend fun getAccountInfo(
        accountRepo: AccountRepo
    ): AccountInfo {
        if (accountInfo == null) {
            val id = getId()
            when (val result = accountRepo.getAccountInfo(id)) {
                is Success -> {
                    accountInfo = result.data
                }
                is Error -> {
                    throw RuntimeException("Account not found")
                }
            }
        }
        return accountInfo!!
    }
}