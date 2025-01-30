package com.akash.bank.authentication.form

import androidx.navigation.NavHostController

sealed class FormAction {
//    data class Login(
//        val action: () -> Unit
//    ) : FormAction()
    data class Login(val navHostController: NavHostController) : FormAction()
    data object GoToRegisterClicked : FormAction()
    data class UpdateLoginEmail(val email: String) : FormAction()
    data class UpdateLoginPassword(val password: String) : FormAction()

    data class Resister(val navHostController: NavHostController) : FormAction()
    data object GoToLoginClicked: FormAction()
    data class UpdateResisterEmail(val email: String): FormAction()
    data class UpdateResisterPassword(val password: String): FormAction()
    data class Init(val navHostController: NavHostController): FormAction()
}