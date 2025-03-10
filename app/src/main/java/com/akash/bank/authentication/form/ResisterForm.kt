package com.akash.bank.authentication.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun RegisterForm(
    state: FormState,
    navHostController: NavHostController,
    onAction: (FormAction) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Register", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.resisterEmail,
            onValueChange = { onAction(FormAction.UpdateResisterEmail(it))  },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.resisterPassword,
            onValueChange = { onAction(FormAction.UpdateResisterPassword(it))  },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            onAction(FormAction.Resister(navHostController))
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Register")
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "Already have an account? ")
            TextButton(onClick = { onAction(FormAction.GoToLoginClicked) }) {
                Text(text = "Login Here")
            }
        }
    }
}