package com.alexsp0.dailyapp.contracts

import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.presenters.MainPresenter

class MainContract {

    interface MainView {
        fun setImage(imageInfo : NasaImageResponse)
    }

    interface MainModel {

        fun loadDailyImage()

        fun setPresenter(presenter: MainPresenter)
    }

}