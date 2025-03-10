package com.akash.bank.di

import com.akash.bank.account.AccountRepo
import com.akash.bank.authentication.AuthRepo
import com.akash.bank.authentication.AuthViewModel
import com.akash.bank.home.HomeViewModel
import com.akash.bank.main.MainViewModel
import com.akash.bank.temporary.TemporaryAccountRepo
import com.akash.bank.temporary.TemporaryAuthRepo
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


var appModule = module {
    viewModel { AuthViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    single { TemporaryAuthRepo(get()) }.bind(AuthRepo::class)
    single { TemporaryAccountRepo(get()) }.bind(AccountRepo::class)
}
