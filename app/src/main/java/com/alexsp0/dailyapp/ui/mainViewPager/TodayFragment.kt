package com.alexsp0.dailyapp.ui.mainViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexsp0.dailyapp.R
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class TodayFragment(private val presenter : MainContract.DatePresenter) : Fragment(), MainContract.ImageDateFragment {
    private lateinit var dateImageView: ImageView
    private lateinit var pictureTitle : TextView
    private lateinit var pictureDate : TextView
    private lateinit var pictureDescription : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateImageView = view.findViewById(R.id.image)
        pictureTitle = view.findViewById(R.id.textview_picture_title)
        pictureDate = view.findViewById(R.id.textview_picture_date)
        pictureDescription = view.findViewById(R.id.textview_picture_description)
        presenter.getImageByDate(getDate())
    }
    private fun getDate() : String {
        val simpleDateFormat = SimpleDateFormat("YYYY-MM-DD", Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter : MainContract.DatePresenter) = TodayFragment(presenter)
    }

    override fun setImageWithDescription(image: NasaImageResponse) {
        Glide.with(this).load(image.url).placeholder(R.drawable.film).into(dateImageView)
        pictureTitle.text = image.title.toString()
        pictureDate.text = image.date.toString()
        pictureDescription.text=image.explanation.toString()
    }
}