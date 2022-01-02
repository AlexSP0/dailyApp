package com.alexsp0.dailyapp.presenters

import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.models.MainModelImpl

class MainPresenterImpl:MainContract.MainPresenter {
    private val model = MainModelImpl()
    override fun getDailyImage() {
        TODO("Not yet implemented")
    }
}