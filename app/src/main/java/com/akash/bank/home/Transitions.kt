package com.akash.bank.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable

@Composable
fun exitTransition() = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(
    animationSpec = tween(
        durationMillis = 300
    )
)

@Composable
fun enterTransition() = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(
    animationSpec = tween(
        durationMillis = 300
    )
)