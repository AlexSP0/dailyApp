package com.alexsp0.dailyapp.models

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.alexsp0.dailyapp.BuildConfig.NASA_API_KEY
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.data.NasaQueryDailyPicture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SHARED_PREFERENCE_FILE = "settings"
private const val IS_DARK_THEME = "isDark"

class MainModelImpl:MainContract.MainModel {
    private lateinit var presenter : MainContract.MainPresenter
    private lateinit var context : Context
    private val baseUrl = "https://api.nasa.gov"

    override fun loadDailyImage() {
       val retrofit = Retrofit.Builder()
           .baseUrl(baseUrl)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       val query:NasaQueryDailyPicture =retrofit.create(NasaQueryDailyPicture::class.java)
        query.loadDailyImage(NASA_API_KEY).enqueue(object : Callback<NasaImageResponse>{
            override fun onResponse(
                call: Call<NasaImageResponse>,
                response: Response<NasaImageResponse>
            ) {
                val body = response.body()
                if(response.isSuccessful && body != null) {
                    presenter.setDailyImage(body)
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                //Ooopps..
            }
        })
    }

    override fun setPresenter(presenter: MainContract.MainPresenter) {
        this.presenter = presenter
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun setTheme(isDark: Boolean) {
        val prefs = context.getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(IS_DARK_THEME, isDark)
        editor.apply()
        presenter.changeTheme(isDark)
    }

    override fun loadThemeIsDark(): Boolean = context
            .getSharedPreferences(SHARED_PREFERENCE_FILE, MODE_PRIVATE)
            .getBoolean(IS_DARK_THEME, false)
}