package com.akash.bank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.cards.CardData
import com.akash.bank.local.LocalResources
import com.akash.bank.main.MainScreenRoute
import com.akash.bank.temporary.LoginStatus
import com.akash.bank.temporary.LoginStatus.HasLoggedIn
import com.akash.bank.ui.auth.AuthScreenRoute
import com.akash.bank.ui.notifications.NotificationData
import com.akash.bank.ui.theme.BankTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                BankTheme {
                    val navHostController = rememberNavController()
                    val authRepo = koinInject<AuthRepo>()
                    val login = authRepo.isLoggedIn()
                    when (login) {
                        is HasLoggedIn -> {
                            LocalResources.setId(login.id)
                        }
                        LoginStatus.HasNotLoggedIn -> { }
                    }
                    val isLoggedIn = login is HasLoggedIn

                    MyNavHostController(
                        navHostController,
                        if (isLoggedIn) MainScreenRoute else AuthScreenRoute
                    )
                }
            }
        }
    }
}
