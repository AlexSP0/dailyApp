package com.alexsp0.dailyapp.presenters

import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.models.MainModelImpl

class MainPresenterImpl:MainContract.MainPresenter {
    private var activity: MainActivity? = null
    private val model = MainModelImpl()

    init{
        model.setPresenter(this)
    }
    override fun getDailyImage() {
        model.loadDailyImage()
    }

    override fun setDailyImage(response: NasaImageResponse) {
            activity?.setImage(response)
    }

    override fun attach(activity: MainActivity) {
        this.activity = activity
    }

    override fun detach() {
        this.activity = null
    }
}