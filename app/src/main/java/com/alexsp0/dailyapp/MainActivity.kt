package com.alexsp0.dailyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexsp0.dailyapp.contracts.MainContract

class MainActivity : AppCompatActivity() {
    private lateinit var presenter :MainContract.MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Получаем presenter из App
        val app = application as App
        presenter= app.presenter
    }
}