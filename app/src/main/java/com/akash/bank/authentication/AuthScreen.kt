package com.akash.bank.ui.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.akash.bank.authentication.AuthViewModel
import com.akash.bank.authentication.form.FormAction
import com.akash.bank.authentication.form.LoginForm
import com.akash.bank.authentication.form.RegisterForm
import com.akash.bank.ui.theme.getGradientColors
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
object AuthScreenRoute

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: AuthViewModel = koinViewModel<AuthViewModel>()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    viewModel.onAction(FormAction.Init(navHostController))
    if (state.error != null) {
        Toast.makeText(context, viewModel.getError().orEmpty(), Toast.LENGTH_SHORT).show()
    }
    if (state.isLoginLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        Surface(
            modifier = modifier
                .background(brush = Brush.verticalGradient(getGradientColors())),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedContent(
                    targetState = state.isInLoginScreen,
                    transitionSpec = {
                        authTransform()
                    }, label = "Form Animation"
                ) { targetState ->
                    when (targetState) {
                        true -> LoginForm(
                            state = state,
                            navHostController = navHostController,
                            onAction = viewModel::onAction
                        )

                        false -> RegisterForm(
                            state = state,
                            navHostController = navHostController,
                            onAction = viewModel::onAction
                        )
                    }
                }
            }
        }
    }
}


private fun AnimatedContentTransitionScope<Boolean>.authTransform(): ContentTransform {
    return if (targetState) {
        (slideInHorizontally(initialOffsetX = { -it }) + fadeIn(
            animationSpec = tween(
                durationMillis = 300
            )
        ))
            .togetherWith(
                slideOutHorizontally(targetOffsetX = { it }) + fadeOut(
                    animationSpec = tween(durationMillis = 200)
                )
            )
    } else {
        (slideInHorizontally(initialOffsetX = { it }) + fadeIn(
            animationSpec = tween(
                durationMillis = 300
            )
        ))
            .togetherWith(
                slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(
                    animationSpec = tween(durationMillis = 200)
                )
            )
    }
}

