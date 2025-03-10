package com.akash.bank.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.akash.bank.home.SavingItemData
import kotlinx.coroutines.delay


@Composable
fun AnimatedSavingItem(savingItemData: SavingItemData, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200) // Delay to allow the screen to render
        visible = true // Update the state to start animation
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
        SavingItem(
            title = savingItemData.title,
            subTitle = savingItemData.subTitle,
            percent = savingItemData.percent,
            cardColor = savingItemData.cardColor,
            onClick = onClick
        )
    }
}