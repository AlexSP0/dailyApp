package com.alexsp0.dailyapp

import android.app.Application
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.presenters.MainPresenterImpl

class App : Application() {
    val presenter = MainPresenterImpl(this)
    override fun onCreate() {
        super.onCreate()
    }

}