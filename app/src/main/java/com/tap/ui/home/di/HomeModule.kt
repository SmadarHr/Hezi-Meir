package com.tap.ui.home.di

import com.tap.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel(
            searchUseCase = get()
        )
    }
}