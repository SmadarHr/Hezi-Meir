package com.tap.ui.empty.di

import com.tap.ui.movie.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val emptyModule = module {

    viewModel {
        VideoViewModel()
    }
}