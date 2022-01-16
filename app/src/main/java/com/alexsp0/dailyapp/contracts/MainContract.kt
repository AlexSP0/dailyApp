package com.alexsp0.dailyapp.contracts

import android.content.Context
import com.alexsp0.dailyapp.MainActivity
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.ui.MainFragment

class MainContract {
    interface MainPresenter {
        fun getDailyImage()
        fun setDailyImage(response : NasaImageResponse)
        fun attach(fragment: MainFragment)
        fun detach()
        fun setTheme(isDark: Boolean)
        fun changeTheme(isDark: Boolean)
        fun isThemeDark() :Boolean
    }
    interface MainModel {
        fun loadDailyImage()
        fun setPresenter(presenter:MainPresenter)
        fun setContext(context: Context)
        fun setTheme(isDark: Boolean)
        fun loadThemeIsDark() :Boolean
    }

}