package com.alexsp0.dailyapp.presenters

import android.content.Context
import com.alexsp0.dailyapp.App
import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.models.MainModelImpl
import com.alexsp0.dailyapp.ui.MainFragment

class MainPresenterImpl(context: Context):MainContract.MainPresenter {
    private var fragment: MainFragment? = null
    private val model = MainModelImpl()

    init{
        model.setPresenter(this)
        model.setContext(context)
    }
    override fun getDailyImage() {
        model.loadDailyImage()
    }

    override fun setDailyImage(response: NasaImageResponse) {
            fragment?.setImage(response)
    }

    override fun attach(fragment: MainFragment) {
        this.fragment = fragment
    }

    override fun detach() {
        this.fragment = null
    }

    override fun setTheme(isDark: Boolean) {
        model.setTheme(isDark)
    }

    override fun changeTheme(isDark: Boolean) {
        fragment?.changeTheme(isDark)
    }

    override fun isThemeDark(): Boolean = model.loadThemeIsDark()

}