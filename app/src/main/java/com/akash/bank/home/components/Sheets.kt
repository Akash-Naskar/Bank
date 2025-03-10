package com.akash.bank.home.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.bank.R
import com.akash.bank.cards.CardData
import com.akash.bank.home.HomeActions

@Composable
fun SheetSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(16.dp)
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardSheet(
    sheetState: SheetState,
    onAction: (action: HomeActions) -> Unit
) {
    var cardHolderName by rememberSaveable { mutableStateOf("") }
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var cardExpiry by rememberSaveable { mutableStateOf("") }
    var cardCvv by rememberSaveable { mutableStateOf("") }
    var cardAmount by remember { mutableIntStateOf(0) }
    var cardPin by rememberSaveable { mutableStateOf("") }
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onAction(HomeActions.ToggleAddCardSheet) },
        modifier = Modifier
            .heightIn(min = 200.dp)
            .imePadding() // Use imePadding for automatic adjustments
    ) {
        val focusManager = LocalFocusManager.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(text = stringResource(R.string.fill_card_details))
            SheetSpacer()
            OutlinedTextField(
                value = cardHolderName,
                onValueChange = {
                    cardHolderName = it
                },
                label = { Text(text = "Holder Name") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )

            )
            SheetSpacer()
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
            SheetSpacer()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExpirySelector(
                    value = cardExpiry,
                    onValueChange = { cardExpiry = it },
                    modifier = Modifier.weight(1f),
                )
                OutlinedTextField(
                    value = cardCvv,
                    onValueChange = { it ->
                        if (it.length == 3)
                            focusManager.moveFocus(FocusDirection.Down)
                        cardCvv = it.filter {s -> s.isDigit() }.take(3)
                    },
                    label = { Text(text = "CVV") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,// Make single line
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                )
            }
            SheetSpacer()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = cardAmount.toString(),
                    onValueChange = {
                        it.toIntOrNull()?.let { amount ->
                            cardAmount = amount
                        }
                    },
                    label = { Text(text = "Balance") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    )
                )
                OutlinedTextField(
                    value = cardPin.map { '*' }.joinToString(""),
                    onValueChange = {
                        if (it.length == 4)
                            focusManager.clearFocus()
                        cardPin = it.take(4)
                    },
                    label = { Text(text = "PIN") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                )
            }
            SheetSpacer()
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                val context = LocalContext.current

                Button(onClick = {
//                    Toast.makeText(
//                        context,
//                        "$cardHolderName $cardNumber $cardExpiry $cardCvv",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    if (cardHolderName.isEmpty() || cardNumber.isEmpty() || cardExpiry.isEmpty() || cardCvv.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Please fill all the fields",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    onAction(HomeActions.AddCard(
                        CardData(
                            cardType = "VISA",
                            cardNumber = cardNumber,
                            expiry = cardExpiry,
                            cvv = cardCvv.toInt(),
                            cardholderName = cardHolderName,
                            balance = cardAmount.toDouble()
                        )
                    ))
                    onAction(HomeActions.ToggleAddCardSheet)
                    // Validate and add card logic here
                }) {
                    Text(text = "Add Card")
                }
            }
        }
    }
}

@Composable
fun ExpirySelector(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
) {
    val month = value.split("/").getOrNull(0) ?: ""
    val year = value.split("/").getOrNull(1) ?: ""
    val focusManager = LocalFocusManager.current
//    val yearFocusRequester = remember {
//        FocusRequester()
//    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = value.split("/").getOrNull(0) ?: "",
            onValueChange = {
                if (it.length == 2)
                    focusManager.moveFocus(FocusDirection.Right)
                onValueChange("${it.take(2)}/$year")
            },
            label = { Text(text = "MM") },
            modifier = Modifier
                .weight(1f),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Text(
            text = "/",
            modifier = Modifier.align(CenterVertically),
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = value.split("/").getOrNull(1) ?: "",
            onValueChange = {
                if (it.length == 2)
                    focusManager.moveFocus(FocusDirection.Right)
                onValueChange("$month/${it.take(2)}")
            },
            label = { Text(text = "YY") },
            modifier = Modifier.weight(1f),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
    }
}

//@SuppressLint("RememberReturnType")
@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth(),

    ) {
    val cardNumberTextField = remember(value) {
        TextFieldValue(
            text = value,
            selection = TextRange(value.length)
        )
    }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = cardNumberTextField,
        onValueChange = {
            if (value.length >= 19) {
                focusManager.clearFocus()
            }
            val formatted = spacedNumber(it.text)
            onValueChange(formatted)
        },
        label = { Text(text = "Card Number") },
        modifier = modifier,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    )
}

fun spacedNumber(cardNumber: String): String = cardNumber.filter { it.isDigit() }
    .take(16)
    .chunked(4)
    .joinTo(StringBuilder(), separator = " ") { it }
    .toString()