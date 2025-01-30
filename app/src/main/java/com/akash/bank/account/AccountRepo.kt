package com.akash.bank.account

import com.akash.bank.cards.CardData
import com.akash.bank.utils.Result

interface AccountRepo {
    suspend fun getAccountInfo(id: String): Result<AccountInfo, AccountErrors>
    suspend fun updateBalance(newBalance: Double): Result<Unit, AccountErrors>
    suspend fun addCard(id: String, cardData: CardData): Result<Unit, AccountErrors>
    suspend fun putAccountInfo(email: String, id: String): Result<Unit, AccountErrors>
    suspend fun updateAccountInfo(accountInfo: AccountInfo): Result<Unit, AccountErrors>
}