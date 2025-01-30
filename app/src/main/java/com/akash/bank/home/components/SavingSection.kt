package com.akash.bank.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.bank.home.SavingItemData


@Composable
fun SavingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Your Saving",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        TextButton(onClick = {
            Log.d("HomeScreen", "View All savings clicked")
        }) {
            Text(text = "View All", fontSize = 16.sp)
        }
    }
    val savingItems = listOf(
        SavingItemData(
            title = "Buy Car Remote",
            subTitle = "Mercedes Benz 001",
            percent = 80,
            cardColor = listOf(Color(0xFF4CAF50), Color(0xFF81C784))
        ),
        SavingItemData(
            title = "Buy Playstation",
            subTitle = null,
            percent = null,
            cardColor = listOf(Color(0xFFFF9800), Color(0xFFF4B74D))
        )
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        (0..5).forEach { _ ->
            savingItems.forEach { savingItem ->
                AnimatedSavingItem(savingItemData = savingItem, onClick = {
                    Log.d("HomeScreen", "Saving item clicked: ${savingItem.title}")
                })
            }
        }
    }

}