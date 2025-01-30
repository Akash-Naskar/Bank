package com.akash.bank.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.akash.bank.account.AccountRepo
import com.akash.bank.cards.CardData
import com.akash.bank.local.LocalResources
import com.akash.bank.utils.or
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val accountRepo: AccountRepo
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        Log.d("HomeViewModel", "init")
        fetchAccountDetails()
    }

    fun getError(): String? {
        val error = _state.value.error
        _state.update {
            it.copy(
                error = null
            )
        }
        return _state.value.error
    }

    private fun fetchAccountDetails() {
        viewModelScope.launch {
            val account = LocalResources.getAccountInfo(accountRepo)
            _state.update {
                it.copy(
                    accountInfo = account
                )
            }
        }
    }

    fun onAction(action: HomeActions) {
        when(action) {
            HomeActions.MakeBalanceVisible -> makeBalanceVisible()
            HomeActions.OnBalanceCardClicked -> onBalanceCardClicked()
            is HomeActions.ToggleAddCardSheet -> navigateToAddCard()
            is HomeActions.NavigateToBorrow -> navigateToBorrow(action.navHostController)
            is HomeActions.NavigateToInvest -> navigateToInvest(action.navHostController)
            is HomeActions.NavigateToNotification -> navigateToNotification(action.navHostController)
            is HomeActions.NavigateToPay -> navigateToPay(action.navHostController)
            is HomeActions.NavigateToSend -> navigateToSend(action.navHostController)
            is HomeActions.NavigateToTopUp -> navigateToTopUp(action.navHostController)
            is HomeActions.UpdateBalance -> onUpdateBalance(action.balance)
            is HomeActions.NavigateToRequest -> navigateToRequest(action.navHostController)
            is HomeActions.AddCard -> addCard(action.cardData)
        }
    }

    private fun addCard(cardData: CardData) {
        val currentCards = _state.value.accountInfo.cardList
        if (currentCards.any { it.cardNumber == cardData.cardNumber }) {
            return
        }
        Log.d("HomeViewModel", "addCard: $cardData")
        val updatedCards = currentCards.toMutableList().apply {
            add(cardData)
        }

        _state.update {
            it.copy(
                accountInfo = it.accountInfo.copy(
                    cardList = updatedCards
                )
            )
        }
    }

    private fun onAddCardClicked() {

    }

    private fun navigateToRequest(navHostController: NavHostController) {
        
    }

    private fun onUpdateBalance(balance: Double) {
        _state.update {
            it.copy(
                accountInfo = it.accountInfo.copy(
                    balance = balance
                )
            )
        }
    }

    private fun navigateToTopUp(navHostController: NavHostController) {

    }

    private fun navigateToSend(navHostController: NavHostController) {

    }

    private fun navigateToPay(navHostController: NavHostController) {

    }

    private fun navigateToNotification(navHostController: NavHostController) {

    }

    private fun navigateToInvest(navHostController: NavHostController) {

    }

    private fun navigateToBorrow(navHostController: NavHostController) {

    }

    private fun navigateToAddCard() {
        _state.update {
            it.copy(
                showAddCardSheet = !it.showAddCardSheet
            )
        }

    }

    private fun onBalanceCardClicked() {

    }

    private fun makeBalanceVisible() {
        _state.update {
            it.copy(
                isBalanceVisible = true
            )
        }
    }
}