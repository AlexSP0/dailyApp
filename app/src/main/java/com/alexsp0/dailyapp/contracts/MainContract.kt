package com.alexsp0.dailyapp.contracts

import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.data.NasaImageResponse

class MainContract {
    interface MainPresenter {
        fun getDailyImage()
        fun setDailyImage(response : NasaImageResponse)
        fun attach(activity : MainActivity)
        fun detach()
    }
    interface MainModel {
        fun loadDailyImage()
        fun setPresenter(presenter:MainPresenter)
    }

}