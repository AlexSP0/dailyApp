package com.alexsp0.dailyapp.presenters

import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.models.MainModelImpl

class MainPresenter  {

    private var fragment: MainContract.MainView? = null

    private val model = MainModelImpl()

    init {
        model.setPresenter(this)
    }

    fun getDailyImage() {
        model.loadDailyImage()
    }

    fun setDailyImage(response: NasaImageResponse) {
        fragment?.setImage(response)
    }

    fun attach(fragment: MainContract.MainView) {
        this.fragment = fragment
    }

    fun detach() {
        this.fragment = null
    }
}