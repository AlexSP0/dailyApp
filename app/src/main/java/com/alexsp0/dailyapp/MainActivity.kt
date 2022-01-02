package com.alexsp0.dailyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var presenter :MainContract.MainPresenter
    private lateinit var img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Получаем presenter из App
        val app = application as App
        presenter= app.presenter
        presenter.attach(this)
        presenter.getDailyImage()
        img = findViewById(R.id.image)
    }
    fun setImage(imageInfo : NasaImageResponse) {
        Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(img)
    }
}