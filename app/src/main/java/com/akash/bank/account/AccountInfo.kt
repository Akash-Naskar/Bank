package com.akash.bank.account

import com.akash.bank.cards.CardData


data class AccountInfo(
    val id: String = "",
    val email: String,
    val profileUrl: String,
    val balance: Double,
    val cardList: List<CardData>,
    val pin: Int,
    val userName: String,
    val countryCode: String = "+91",
    val phoneNumber: Long,
    val hasUserUpdatedAllData: Boolean = false,
)

const val DEFAULT_USER_NAME = ""

const val DEFAULT_PHONE_NUMBER = 0L
const val DEFAULT_PIN = 0
const val DEFAULT_COUNTRY_CODE = "+91"
const val DEFAULT_PROFILE_URL = "https://avatar.iran.liara.run/public/19"
const val DEFAULT_BALANCE = 5000.0

fun AccountInfo.isDefault(): Boolean {
    return this.userName == DEFAULT_USER_NAME &&
            this.phoneNumber == DEFAULT_PHONE_NUMBER &&
            this.pin == DEFAULT_PIN &&
            this.countryCode == DEFAULT_COUNTRY_CODE &&
            this.profileUrl == DEFAULT_PROFILE_URL &&
            this.balance == DEFAULT_BALANCE
}


fun getDefaultUserData(id: String, email: String) = AccountInfo(
    id = id,
    email = email,
    profileUrl = DEFAULT_PROFILE_URL,
    balance = DEFAULT_BALANCE,
    cardList = emptyList(),
    pin = DEFAULT_PIN,
    userName = DEFAULT_USER_NAME,
    phoneNumber = DEFAULT_PHONE_NUMBER,
    countryCode = DEFAULT_COUNTRY_CODE,
)


