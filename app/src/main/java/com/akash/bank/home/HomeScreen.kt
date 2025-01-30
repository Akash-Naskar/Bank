package com.akash.bank.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.akash.bank.R
import com.akash.bank.home.components.AddCardSheet
import com.akash.bank.home.components.BalanceAndActions
import com.akash.bank.home.components.CardSection
import com.akash.bank.home.components.HeaderSection
import com.akash.bank.home.components.SavingSection
import com.akash.bank.ui.theme.getGradientColors
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navHostController: NavHostController,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val onAction = viewModel::onAction
    val addCardSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(getGradientColors())),

    ) {
        HandleAllSheets(
            state = state,
            onAction = onAction,
            addCardSheetState = addCardSheetState
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(state.accountInfo.userName) {
                onAction(HomeActions.NavigateToNotification(navHostController))
            }

            BalanceAndActions(
                state = state,
                onAction = onAction,
                navHostController = navHostController
            )
            CardSection(
                cardList = state.accountInfo.cardList,
                isCardListLoading = state.isCardListLoading,
                onAction = onAction
            )

//            InvestAndBorrow(
//                onNavigateToInvest = onNavigateToInvest,
//                onNavigateToBorrow = onNavigateToBorrow
//            )
            SavingSection()
        }


    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HandleAllSheets(
    state: HomeState,
    onAction: (action: HomeActions) -> Unit,
    addCardSheetState: SheetState
) {
    if (state.showAddCardSheet) {
        AddCardSheet(
            sheetState = addCardSheetState,
            onAction = onAction
        )
    }
}



//@Composable
//fun StatusBarArea() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(24.dp) // Height of the status bar
//            .padding(bottom = 5.dp)
//    ) {
//        // Can add system time or system icon in future if required
//    }
//}














data class SavingItemData(
    val title: String,
    val subTitle: String?,
    val percent: Int?,
    val cardColor: List<Color>
)



@Composable
fun InvestAndBorrow(onNavigateToInvest: () -> Unit, onNavigateToBorrow: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp), contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Extras",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp))
                            .clickable {
                                Log.d("HomeScreen", "Invest Clicked")
                                onNavigateToInvest()
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F1F4))
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.invest),
                                contentDescription = "Invest",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Blue
                            )
                            Text(
                                text = "Invest",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp))
                            .clickable {
                                Log.d("HomeScreen", "Borrow Clicked")
                                onNavigateToBorrow()
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F1F4))
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.invest),
                                contentDescription = "Borrow",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Blue
                            )
                            Text(
                                text = "Borrow",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

        }

    }
}
