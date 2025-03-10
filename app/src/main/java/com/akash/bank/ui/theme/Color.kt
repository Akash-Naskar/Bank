package com.akash.bank.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Composable
fun getGradientColors() = if (isSystemInDarkTheme()) {
    darkGradientColors
} else {
    lightGradientColors
}
val lightGradientColors = listOf(
    Color(0xFFF0F0F0),
    Color(0xFFE0E0E0)
)
val darkGradientColors = listOf(
    Color(0xFF0F1B2B), // Darker shade of the base color
    Color(0xFF16213A), // Base color
    Color(0xFF1B263B)  // Slightly lighter shade for a smooth transition
)



fun Color.toHsl(): FloatArray {
    val redComponent = red
    val greenComponent = green
    val blueComponent = blue

    val maxComponent = maxOf(redComponent, greenComponent, blueComponent)
    val minComponent = minOf(redComponent, greenComponent, blueComponent)
    val delta = maxComponent - minComponent
    val lightness = (maxComponent + minComponent) / 2

    val hue: Float
    val saturation: Float

    if (maxComponent == minComponent) {
        // Grayscale color, no saturation and hue is undefined
        hue = 0f
        saturation = 0f
    } else {
        // Calculating saturation
        saturation = if (lightness > 0.5) delta / (2 - maxComponent - minComponent) else delta / (maxComponent + minComponent)
        // Calculating hue
        hue = when (maxComponent) {
            redComponent -> 60 * ((greenComponent - blueComponent) / delta % 6)
            greenComponent -> 60 * ((blueComponent - redComponent) / delta + 2)
            else -> 60 * ((redComponent - greenComponent) / delta + 4)
        }
    }

    // Returning HSL values, ensuring hue is within 0-360 range
    return floatArrayOf(hue.coerceIn(0f, 360f), saturation, lightness)
}

fun hslToColor(hue: Float, saturation: Float, lightness: Float): Color {
    val chroma = (1 - kotlin.math.abs(2 * lightness - 1)) * saturation
    val secondaryColorComponent = chroma * (1 - kotlin.math.abs((hue / 60) % 2 - 1))
    val matchValue = lightness - chroma / 2

    var red = matchValue
    var green = matchValue
    var blue = matchValue

    when ((hue.toInt() / 60) % 6) {
        0 -> { red += chroma; green += secondaryColorComponent }
        1 -> { red += secondaryColorComponent; green += chroma }
        2 -> { green += chroma; blue += secondaryColorComponent }
        3 -> { green += secondaryColorComponent; blue += chroma }
        4 -> { red += secondaryColorComponent; blue += chroma }
        5 -> { red += chroma; blue += secondaryColorComponent }
    }

    // Creating a color from RGB components
    return Color(red = red, green = green, blue = blue)
}

fun Color.setSaturation(newSaturation: Float): Color {
    val hslValues = this.toHsl()
    // Adjusting the saturation while keeping hue and lightness the same
    return hslToColor(hslValues[0], newSaturation.coerceIn(0f, 1f), hslValues[2])
}
