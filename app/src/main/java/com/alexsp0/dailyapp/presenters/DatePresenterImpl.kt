package com.alexsp0.dailyapp.presenters

import androidx.fragment.app.Fragment
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.models.DateModelImpl
import com.alexsp0.dailyapp.ui.mainViewPager.TodayFragment

class DatePresenterImpl :MainContract.DatePresenter{
    private val model = DateModelImpl(this)
    private var fragment : MainContract.ImageDateFragment? = null

    override fun getImageByDate(date: String) {
        model.loadImageByDate(date)
    }

    override fun setImage(image : NasaImageResponse) {
        fragment?.setImageWithDescription(image)
    }

    override fun attach(fragment: MainContract.ImageDateFragment) {
        this.fragment = fragment
    }

    override fun detach() {
        this.fragment = null
    }

}