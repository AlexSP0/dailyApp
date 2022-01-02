package com.alexsp0.dailyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alexsp0.dailyapp.R
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.presenters.MainPresenterImpl
import com.bumptech.glide.Glide

class MainFragment(presenter:MainPresenterImpl) : Fragment() {
    private var presenter : MainPresenterImpl = presenter
    private lateinit var image : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        image = view.findViewById(R.id.image)
        presenter.getDailyImage()
        return view
    }

    fun setImage(imageInfo : NasaImageResponse) {
        Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(image)
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: MainPresenterImpl) = MainFragment(presenter)
    }
}