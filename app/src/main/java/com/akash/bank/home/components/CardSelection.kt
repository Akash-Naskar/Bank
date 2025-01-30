package com.akash.bank.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.bank.cards.CardData
import com.akash.bank.home.HomeActions
import com.akash.bank.home.components.card.AnimatedCardItem


@Composable
fun CardSection(cardList: List<CardData>, isCardListLoading: Boolean, onAction: (HomeActions) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Your Cards",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        TextButton(onClick = {
            onAction(HomeActions.ToggleAddCardSheet)
        }) {
            Text(text = "Add a card", fontSize = 16.sp)
        }
    }
    if (!isCardListLoading) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            items(
                cardList,
                key = {
                    it.cardNumber
                }
            ) { card ->
                AnimatedCardItem(
                    cardItemData = card,
                    onClick = {
                        Log.d("CardSection", "Card clicked")
                    }
                )
            }

        }
    }
    else {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(text = "Loading your cards...", fontSize = 16.sp)
        }
    }
}