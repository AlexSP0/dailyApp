package com.alexsp0.dailyapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaQueryDailyPictureByDate {
    @GET("planetary/apod")
    fun loadDailyImage(@Query("api_key") apiKey: String, @Query("date") date : String) : Call<NasaImageResponse>
}