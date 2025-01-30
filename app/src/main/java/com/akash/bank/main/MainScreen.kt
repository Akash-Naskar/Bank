package com.akash.bank.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import com.akash.bank.account.AccountRepo
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.home.HomeScreen
import com.akash.bank.home.HomeViewModel
import com.akash.bank.local.LocalResources
import com.akash.bank.temporary.LoginStatus.HasNotLoggedIn
import com.akash.bank.temporary.navigation.NavigationItems
import com.akash.bank.ui.auth.AuthScreenRoute
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Serializable
object MainScreenRoute

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    var currentDestination by rememberSaveable { mutableStateOf(NavigationItems.HOME) }
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            NavigationSuiteType.NavigationBar
        } else {
            NavigationSuiteType.NavigationRail
        }
    }
    viewModel.initViewModel(navHostController)
    NavigationSuiteScaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        layoutType = customNavSuiteType,
        navigationSuiteItems = {
            NavigationItems.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.unSelectedIcon ?: ImageVector.vectorResource(it.unSelectedIconResId),
                            contentDescription = stringResource(it.contentDescription)
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                )
            }
        }
    ) {
        Scaffold { innerPadding ->
            Box(
                Modifier.padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {

                when (currentDestination) {
                    NavigationItems.HOME ->
                        HomeScreen(
                            navHostController = navHostController,
                            viewModel = koinViewModel()
                        )
                    NavigationItems.HISTORY -> Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                    )

                    NavigationItems.PROFILE -> Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    )
                }
            }
        }

    }
}