package com.alexsp0.dailyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.ui.MainFragment
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var presenter :MainContract.MainPresenter
    private lateinit var img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as App
        loadFragment(MainFragment.newInstance(app.presenter))
    }

    fun loadFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }
}