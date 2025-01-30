package com.akash.bank.temporary

import android.content.Context
import android.widget.Toast
import com.akash.bank.account.AccountErrors
import com.akash.bank.account.AccountInfo
import com.akash.bank.account.AccountRepo
import com.akash.bank.account.getDefaultUserData
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.cards.CardData
import com.akash.bank.utils.Result
import kotlinx.coroutines.delay

class TemporaryAccountRepo(
    private val context: Context,
) : AccountRepo {
    private val accountInfoPool: MutableList<AccountInfo> = mutableListOf()

    init {
        val cards = mutableListOf<CardData>()
        cards.add(
            CardData(
                cardType = "VISA",
                cardNumber = "1234 5678 9012 3456",
                expiry = "12/23",
                cvv = 123,
                cardholderName = "Nezuko Chan",
                balance = 10000.0
            )
        )
        accountInfoPool.add(
            AccountInfo(
                id = "1",
                email = "test@gmail.com",
                profileUrl = "",
                balance = 1000.0,
                cardList = cards,
                pin = 1234,
                userName = "Nezuko Chan",
                phoneNumber = 1234567890
            )
        )
        accountInfoPool.add(
            getDefaultUserData("2", "")
        )

    }

    override suspend fun getAccountInfo(id: String): Result<AccountInfo, AccountErrors> {
        delay(20)
        val info = accountInfoPool.find { it.id == id }
        if (info == null) {
            return Result.Error(AccountErrors.ACCOUNT_NOT_FOUND)
        }
        return Result.Success(info)
    }

    override suspend fun putAccountInfo(email: String, id: String): Result<Unit, AccountErrors> {
        delay(20)
        accountInfoPool.add(getDefaultUserData(id, email))
        return Result.Success(Unit)
    }

    override suspend fun updateAccountInfo(accountInfo: AccountInfo): Result<Unit, AccountErrors> {
        delay(20)
        val info = accountInfoPool.find { it.id == accountInfo.id }
        if (info == null) {
            return Result.Error(AccountErrors.ACCOUNT_NOT_FOUND)
        }
        accountInfoPool.remove(info)
        accountInfoPool.add(accountInfo)
        return Result.Success(Unit)
    }

    override suspend fun updateBalance(newBalance: Double): Result<Unit, AccountErrors> {
        delay(200)
        return Result.Success(Unit)
//        var error: AccountErrors? = null
//        val result = getAccountInfo()
//        result.onSuccess { info ->
//            this.accountInfo = info.copy(balance = newBalance)
//        }
//        result.onError {
//            error = it
//            Toast.makeText(context, "Error updating balance", Toast.LENGTH_SHORT).show()
//        }
//        return if (error != null) {
//            Result.Error(error!!)
//        } else {
//            Result.Success(Unit)
//        }
    }

    override suspend fun addCard(id: String, cardData: CardData): Result<Unit, AccountErrors> {
        delay(20)
        val info = accountInfoPool.find { it.id == id }
        if (info == null) {
            return Result.Error(AccountErrors.ACCOUNT_NOT_FOUND)
        }
        val cards = info.cardList.toMutableList()
        cards.add(cardData)
        val newInfo = info.copy(cardList = cards)
        accountInfoPool.remove(info)
        accountInfoPool.add(newInfo)
        return Result.Success(Unit)
    }
}