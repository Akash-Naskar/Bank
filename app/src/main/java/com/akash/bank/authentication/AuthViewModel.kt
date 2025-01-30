package com.akash.bank.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.akash.bank.account.AccountRepo
import com.akash.bank.authentication.form.FormAction
import com.akash.bank.authentication.form.FormState
import com.akash.bank.local.LocalResources
import com.akash.bank.main.MainScreenRoute
import com.akash.bank.temporary.LoginStatus
import com.akash.bank.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepo: AuthRepo,
    private val accountRepo: AccountRepo
): ViewModel() {


    private val _state: MutableStateFlow<FormState> = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    fun getError(): String? {
        val error = _state.value.error ?: return null
        _state.update {
            it.copy(
                error = null
            )
        }
        return error
    }

    init {
//        when(val isLoggedIn = authRepo.isLoggedIn()){
//            is LoginStatus.HasLoggedIn -> {
//                LocalResources.setId(isLoggedIn.id)
//                _state.update {
//                    it.copy(
//                        isInLoginScreen = false
//                    )
//                }
//            }
//            is LoginStatus.HasNotLoggedIn -> {
//                _state.update {
//                    it.copy(
//                        isInLoginScreen = true
//                    )
//                }
//            }
//        }
    }

    private fun onLoginClicked() {
        _state.update { it.copy(isInLoginScreen = true) }
    }

    private fun onRegisterClicked() {
        _state.update { it.copy(isInLoginScreen = false) }
    }

    private fun login(navHostController: NavHostController) {
        viewModelScope.launch {
            _state.update { it.copy(isLoginLoading = true) }
            when (val r = authRepo.login(
                _state.value.loginEmail,
                _state.value.loginPassword
            )) {
                is Result.Error -> {
//                    Log.d("AuthViewModel", "login: ${r.error}")
                    _state.update { it.copy(error = r.error.toString()) }
                }
                is Result.Success -> {
                    LocalResources.setId(r.data)
                    navHostController.navigate(MainScreenRoute)
                }
            }
            _state.update { it.copy(isLoginLoading = false) }
        }
    }

    private fun resister(navHostController: NavHostController) {
        viewModelScope.launch {
            _state.update { it.copy(isLoginLoading = true) }
            val id = authRepo.register(_state.value.resisterEmail, _state.value.resisterPassword)
            when (id) {
                is Result.Error -> {
                    _state.update { it.copy(error = id.error.toString()) }
                }
                is Result.Success -> {
                    LocalResources.setId(id.data)
                    accountRepo.putAccountInfo(_state.value.resisterEmail, id.data)
                }
            }
            _state.update { it.copy(isLoginLoading = false) }
        }
    }

    fun onAction(formAction: FormAction) {
        when(formAction){
            FormAction.GoToLoginClicked -> onLoginClicked()
            FormAction.GoToRegisterClicked -> onRegisterClicked()
            is FormAction.Login -> login(formAction.navHostController)
            is FormAction.Resister -> resister(formAction.navHostController)
            is FormAction.UpdateLoginEmail -> onUpdateLoginEmail(formAction.email)
            is FormAction.UpdateLoginPassword -> onUpdateLoginPassword(formAction.password)
            is FormAction.UpdateResisterEmail -> onUpdateResisterEmail(formAction.email)
            is FormAction.UpdateResisterPassword -> onUpdateResisterPassword(formAction.password)
            is FormAction.Init -> onInit(formAction.navHostController)
        }
    }

    private fun onInit(navHostController: NavHostController) {
        val isLoggedIn = authRepo.isLoggedIn()
        if (isLoggedIn is LoginStatus.HasLoggedIn) {
            LocalResources.setId(isLoggedIn.id)
            navHostController.navigate(MainScreenRoute)
        }
    }

    private fun onUpdateResisterPassword(password: String) {
        _state.update { it.copy(resisterPassword = password) }
    }
    private fun onUpdateResisterEmail(email: String) {
        _state.update { it.copy(resisterEmail = email) }
    }

    private fun onUpdateLoginPassword(password: String) {
        _state.update { it.copy(loginPassword = password) }
    }

    private fun onUpdateLoginEmail(email: String) {
        _state.update { it.copy(loginEmail = email) }
    }
}