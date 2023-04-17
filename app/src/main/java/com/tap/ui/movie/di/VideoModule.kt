package com.tap.ui.movie.di

import com.tap.ui.movie.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val videoModule = module {

    viewModel {
        VideoViewModel()
    }
}