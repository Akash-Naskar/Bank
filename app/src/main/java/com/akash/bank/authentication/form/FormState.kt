package com.akash.bank.authentication.form

data class FormState(
    val loginEmail: String = "",
    val loginPassword: String = "",

    val error: String? = null,
    val resisterPassword: String = "",
    val resisterEmail: String = "",

    val isInLoginScreen: Boolean = true,

    val isLoginLoading: Boolean = false,
)