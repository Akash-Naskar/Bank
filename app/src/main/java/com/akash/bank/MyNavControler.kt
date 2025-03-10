package com.akash.bank

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akash.bank.account.FillAccountInfoRoute
import com.akash.bank.account.FillAccountInfoScreen
import com.akash.bank.main.MainScreen
import com.akash.bank.main.MainScreenRoute
import com.akash.bank.ui.auth.AuthScreen
import com.akash.bank.ui.auth.AuthScreenRoute
import kotlinx.serialization.Serializable

@Composable
fun MyNavHostController(navController: NavHostController, startDestination: @Serializable Any) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable<AuthScreenRoute>{
            AuthScreen(
                navHostController = navController
            )
        }
        composable<MainScreenRoute>{
            MainScreen(
                navHostController = navController,
            )
        }
        composable<FillAccountInfoRoute> {
            FillAccountInfoScreen(
                navHostController = navController
            )
        }
    }
}
