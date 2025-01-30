package com.akash.bank.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF278EA5), // A soft and bright teal as the primary color
    onPrimary = Color.White, // White text on primary for good readability
    secondary = Color(0xFF1F4287), // A darker blue for secondary accents
    onSecondary = Color.White, // White text on secondary
    background = Color(0xFFF4F9FF), // A light, almost white blue for background
    onBackground = Color(0xFF1F1F1F), // Dark text for good contrast on light background
    surface = Color(0xFFEAEAEA), // Light gray for surfaces
    onSurface = Color(0xFF1B263B) // Dark navy text for surfaces
)


private val DarkColors = darkColorScheme(
    primary = Color(0xFF1F4287), // A slightly brighter shade of the base for primary
    onPrimary = Color.White, // Text color on primary
    secondary = Color(0xFF278EA5), // A complementary shade to the base for accents
    onSecondary = Color.White, // Text color on secondary
    background = Color(0xFF16213A), // Base color for background
    onBackground = Color(0xFFEAEAEA), // Light gray text for good contrast
    surface = Color(0xFF1B263B), // Slightly lighter than the background for surfaces
    onSurface = Color(0xFFDADADA) // Light gray text for surfaces
)



@Composable
fun BankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme){
        DarkColors
    }else{
        LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}