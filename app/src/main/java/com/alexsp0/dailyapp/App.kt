package com.alexsp0.dailyapp

import android.app.Application
import com.alexsp0.dailyapp.presenters.MainPresenter

class App : Application() {
    val presenter = MainPresenter()
    override fun onCreate() {
        super.onCreate()
    }

}