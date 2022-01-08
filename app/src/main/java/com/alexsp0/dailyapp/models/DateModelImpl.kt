package com.alexsp0.dailyapp.models

import com.alexsp0.dailyapp.BuildConfig
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.data.NasaQueryDailyPicture
import com.alexsp0.dailyapp.data.NasaQueryDailyPictureByDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DateModelImpl(private val presenter : MainContract.DatePresenter) : MainContract.DateModel {
    private val baseUrl = "https://api.nasa.gov"

    override fun loadImageByDate(date: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val query: NasaQueryDailyPictureByDate =retrofit.create(NasaQueryDailyPictureByDate::class.java)
        query.loadDailyImage(BuildConfig.NASA_API_KEY, date).enqueue(object :
            Callback<NasaImageResponse> {
            override fun onResponse(
                call: Call<NasaImageResponse>,
                response: Response<NasaImageResponse>
            ) {
                val body = response.body()
                if(response.isSuccessful && body != null) {
                    presenter.setImage(body)
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                //Ooopps..
            }
        })
    }

}