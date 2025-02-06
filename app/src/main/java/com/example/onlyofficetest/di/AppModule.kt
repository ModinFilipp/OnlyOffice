package com.example.onlyofficetest.di

import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.data.repository.AuthRepository
import com.example.onlyofficetest.presentation.screens.authorization.AuthorizationViewModel
import com.example.onlyofficetest.presentation.screens.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { DataStoreManager(androidContext()) }
    single { AuthRepository() }

    viewModel { AuthorizationViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}