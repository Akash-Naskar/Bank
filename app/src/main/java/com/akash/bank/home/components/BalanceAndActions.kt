package com.akash.bank.home.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.akash.bank.R
import com.akash.bank.home.HomeActions
import com.akash.bank.home.HomeState
import com.akash.bank.home.enterTransition
import com.akash.bank.home.exitTransition


data class ActionItem(
    val title: String,
    val imageVector: ImageVector? = null,
    val iconResId: Int = 0,
    val onClick: () -> Unit
)


fun getAllActions(
    onAction: (HomeActions) -> Unit,
    navHostController: NavHostController
): List<ActionItem> {
    var items = mutableListOf<ActionItem>()
    items.add(
        ActionItem(
            title = "Send",
            imageVector = Icons.AutoMirrored.Filled.Send,
            onClick = {
                onAction(HomeActions.NavigateToSend(navHostController))
            }
        )
    )
    items.add(
        ActionItem(
            title = "Top Up",
            imageVector = Icons.Default.Add,
            onClick = {
                onAction(HomeActions.NavigateToTopUp(navHostController))
            }
        )
    )
    items.add(
        ActionItem(
            title = "Request",
            imageVector = Icons.Default.Check,
            onClick = {
                onAction(HomeActions.NavigateToRequest(navHostController))
            }
        )
    )
    items.add(
        ActionItem(
            title = "Pending",
            imageVector = null,
            iconResId = R.drawable.pending,
            onClick = {
                onAction(HomeActions.NavigateToSend(navHostController))
            }
        )
    )
    return items
}


@Composable
fun BalanceAndActions(
    state: HomeState,
    onAction: (HomeActions) -> Unit,
    navHostController: NavHostController,
) {
    val visible = state.isBalanceVisible

    onAction(HomeActions.MakeBalanceVisible)
    val context = LocalContext.current

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition(),
        exit = exitTransition()
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAction(HomeActions.OnBalanceCardClicked)
                },
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
//                        Modifier.background(Color.Black)
                    ) {
                        Icon(
                            ImageVector.vectorResource(R.drawable.my_wallet),
                            stringResource(R.string.my_wallet)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "My Wallet",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                        )
                    }
                    Text(
                        text = "Rs. ${state.accountInfo.balance}",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    getAllActions(onAction, navHostController).forEach { actionItem ->

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    Toast.makeText(context, actionItem.title, Toast.LENGTH_SHORT)
                                        .show()
                                    actionItem.onClick()
                                },
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                                shape = RoundedCornerShape(12.dp),
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                containerColor =  MaterialTheme.colorScheme.surfaceVariant,
                            ) {
                                Icon(
                                    imageVector = actionItem.imageVector
                                        ?: ImageVector.vectorResource(actionItem.iconResId),
                                    contentDescription = actionItem.title
                                )
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(
                                text = actionItem.title,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


