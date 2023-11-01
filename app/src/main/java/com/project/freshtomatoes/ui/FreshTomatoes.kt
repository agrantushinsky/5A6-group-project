package com.project.freshtomatoes.ui

import android.app.Application

class FreshTomatoes: Application() {
    /* Always be able to access the module ("static") */
    companion object {
        lateinit var appModule: AppModule
    }

    /* Called only once at beginning of application's lifetime */
    override fun onCreate() {
        super.onCreate()
        appModule = AppModule(this)
    }
}
