package com.alexsp0.dailyapp.models

import com.alexsp0.dailyapp.BuildConfig.NASA_API_KEY
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.data.NasaQueryDailyPicture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainModelImpl:MainContract.MainModel {
    private lateinit var presenter : MainContract.MainPresenter
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
}