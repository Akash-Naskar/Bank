package com.akash.bank.cards

data class CardData(
    val cardType:String,
    val cardNumber: String,
    val expiry: String,
    val cvv: Int,
    var cardholderName: String,
    val balance: Double
)