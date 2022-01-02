package com.alexsp0.dailyapp.contracts

class MainContract {
    interface MainPresenter {
        fun getDailyImage()
    }
    interface MainModel {
        fun loadDailyImage()
    }

}