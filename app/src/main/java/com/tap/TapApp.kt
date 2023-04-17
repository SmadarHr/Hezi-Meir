package com.tap

import android.app.Application
import com.tap.init.AppInitComponent

class TapApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitComponent(this).init()
    }
}