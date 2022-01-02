package com.alexsp0.dailyapp.contracts

import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.ui.MainFragment

class MainContract {
    interface MainPresenter {
        fun getDailyImage()
        fun setDailyImage(response : NasaImageResponse)
        fun attach(fragment: MainFragment)
        fun detach()
    }
    interface MainModel {
        fun loadDailyImage()
        fun setPresenter(presenter:MainPresenter)
    }

}