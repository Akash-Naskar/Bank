package com.akash.bank.home.components.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akash.bank.cards.CardData
import com.akash.bank.home.enterTransition
import com.akash.bank.home.exitTransition
import kotlinx.coroutines.delay



@Composable
fun AnimatedCardItem(cardItemData: CardData, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(200) // Delay to allow the screen to render
        visible = true // Update the state to start animation
    }

        AnimatedVisibility(
            visible = visible,
            enter = enterTransition(),
            exit = exitTransition()
        ) {
            CardItem(
                cardItemData = cardItemData,
                onClick = onClick,
                modifier = Modifier.width(320.dp)
            )
        }

}
