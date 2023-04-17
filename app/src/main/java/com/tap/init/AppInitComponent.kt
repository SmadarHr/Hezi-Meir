package com.tap.init

import android.content.Context

class AppInitComponent(context: Context) : InitComponent {

    private val context = context.applicationContext

    private val initComponentList = listOf(
        KoinInit(this.context)
    )

    override fun init() {
        for (initComponent in initComponentList) {
            initComponent.init()
        }
    }
}