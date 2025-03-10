package com.akash.bank.temporary.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.akash.bank.R

data class NavItem(
    val title: String,
    val isSelected: Boolean,
    val onClick: () -> Unit,
    val icon: ImageVector
)

enum class NavigationItems(
    @StringRes val label: Int,
    val unSelectedIcon: ImageVector? = null,
    val unSelectedIconResId: Int = 0,
    val selectedIcon: ImageVector? = null,
    val selectedIconResId: Int = 0,
    @StringRes val contentDescription: Int,
) {
    HOME(
        label = R.string.home,
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        contentDescription = R.string.home
    ),
    HISTORY(
        label = R.string.history,
        unSelectedIconResId = R.drawable.baseline_history_24,
        selectedIconResId = R.drawable.baseline_history_24,
        contentDescription = R.string.history
    ),
    PROFILE(
        label = R.string.profile,
        unSelectedIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle,
        contentDescription = R.string.profile
    )
}


@Composable
fun NavigationNavigationTray() {
    var currentDestination by rememberSaveable { mutableStateOf(NavigationItems.HOME) }
    NavigationSuiteScaffold(
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
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        // TODO: Destination content.
    }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 40.dp),
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        BottomNavItem("Home", isSelected = true, onClick = {
//            Log.d("HomeScreen", "Home nav button clicked")
//        })
//        BottomNavItem("History", onClick = {
//            Log.d("HomeScreen", "History nav button clicked")
//        })
//        BottomNavItem("Profile", onClick = {
//            Log.d("HomeScreen", "Profile nav button clicked")
//        })
//    }
}

