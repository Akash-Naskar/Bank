package com.akash.bank.temporary.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavItem(title: String, isSelected: Boolean = false, onClick: () -> Unit) {
    val textColor = if (isSelected) Color.Black else Color.Gray
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick()
        }) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = title,
            modifier = Modifier.size(30.dp),
            tint = textColor
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                color = textColor,
                fontWeight = fontWeight,
                fontSize = 14.sp
            )
        )
    }
}