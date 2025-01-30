package com.akash.bank.home

import androidx.navigation.NavHostController
import com.akash.bank.cards.CardData

sealed class HomeActions {
//    data class NavigateToSend(something: ): HomeActions()
    data class NavigateToSend(val navHostController: NavHostController): HomeActions()
    data class NavigateToTopUp(val navHostController: NavHostController): HomeActions()
    data class NavigateToNotification(val navHostController: NavHostController): HomeActions()
    data class NavigateToPay(val navHostController: NavHostController): HomeActions()
    data class NavigateToInvest(val navHostController: NavHostController): HomeActions()
    data class NavigateToBorrow(val navHostController: NavHostController): HomeActions()
    data class UpdateBalance(val balance: Double): HomeActions()
    data object OnBalanceCardClicked: HomeActions()

    data class AddCard(val cardData: CardData): HomeActions()

    data object ToggleAddCardSheet: HomeActions()
//    data object OnAddCardClicked: HomeActions()
    data object MakeBalanceVisible: HomeActions()
    // TODO after 200 ms
    data class NavigateToRequest(val navHostController: NavHostController): HomeActions()
}