package com.tap.init

import android.content.Context
import com.tap.network.di.networkModule
import com.tap.repository.di.repositoryModule
import com.tap.ui.empty.di.emptyModule
import com.tap.ui.home.di.homeModule
import com.tap.ui.movie.di.videoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class KoinInit(context: Context) : InitComponent {

    private val context = context.applicationContext

    override fun init() {
        startKoin {
            androidContext(context.applicationContext)
            modules(
                getModules()
            )
        }
    }

    private fun getModules(): List<Module> {
        return listOf(
            repositoryModule,
            networkModule,
            emptyModule,
            homeModule,
            videoModule
        )
    }
}