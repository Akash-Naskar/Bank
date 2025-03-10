package com.akash.bank.main

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.akash.bank.account.AccountRepo
import com.akash.bank.account.FillAccountInfoRoute
import com.akash.bank.account.isDefault
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.local.LocalResources
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext.get

class MainViewModel(
    val authRepo: AuthRepo,
    private val accountRepo: AccountRepo
) : ViewModel() {
    fun initViewModel(navHostController: NavHostController) {
        viewModelScope.launch {
            val account = LocalResources.getAccountInfo(accountRepo)
            if (account.isDefault()) {
//                navHostController.navigate(FillAccountInfoRoute)
                Toast.makeText(
                    get().get(),
                    "Account Data is not Filled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}