package com.akash.bank.account

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.akash.bank.main.MainScreenRoute
import kotlinx.serialization.Serializable

@Serializable
object FillAccountInfoRoute

@Composable
fun FillAccountInfoScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val backCallback = remember {
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {

            }
        }
    }

    SideEffect {
        (context as? ComponentActivity)?.onBackPressedDispatcher?.addCallback(backCallback)
    }


    Scaffold(

    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Using Default Account Details Because it is not Implemented",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                onClick = {
                    navHostController.navigate(MainScreenRoute)
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text("Go to Main Screen")
            }
        }
    }
}