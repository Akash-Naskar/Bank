package com.akash.bank.home

import com.akash.bank.account.AccountInfo
import com.akash.bank.account.getDefaultUserData
import com.akash.bank.cards.CardData

data class HomeState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val accountInfo: AccountInfo = getDefaultUserData("", ""),
    val isCardListLoading: Boolean = false,
    val isBalanceVisible: Boolean = false,

    val showAddCardSheet: Boolean = false
)