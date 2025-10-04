package com.hack.opensdk

import android.app.Application
import android.content.Context

open class HackApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        application = this
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        @JvmStatic
        lateinit var application: Application
            private set

        @JvmStatic
        fun getInstance(): Application = application
    }
}
