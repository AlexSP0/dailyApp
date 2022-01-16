package com.alexsp0.dailyapp.ui.mainViewPager

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
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

class DateFragment(private val presenter : MainContract.DatePresenter) : Fragment(), MainContract.ImageDateFragment {
    private lateinit var dateImageView: ImageView
    private lateinit var pictureTitle : TextView
    private lateinit var pictureDate : TextView
    private lateinit var pictureDescription : TextView
    private var date : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateImageView = view.findViewById(R.id.image)
        dateImageView = view.findViewById(R.id.image)
        pictureTitle = view.findViewById(R.id.textview_picture_title)
        pictureDate = view.findViewById(R.id.textview_picture_date)
        pictureDescription = view.findViewById(R.id.textview_picture_description)
    }

    fun checkDate() {
        if(date == null) {
            showDateDialog()
        } else {
            presenter.getImageByDate(date!!)
        }
    }
    fun clearDate() {
        date = null
        checkDate()
    }

    private fun showDateDialog() {
        val calendar = Calendar.getInstance(Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day  = calendar.get(Calendar.DAY_OF_MONTH)
        val dlg = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val simpleDateFormat = SimpleDateFormat("YYYY-MM-DD", Locale.getDefault())
            calendar.set(year, month,dayOfMonth)
            val chosenTime = simpleDateFormat.format(calendar.time)
            this.date = chosenTime
            presenter.getImageByDate(chosenTime)
        }, year, month, day).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter : MainContract.DatePresenter)  = DateFragment(presenter)
    }

    override fun setImageWithDescription(image: NasaImageResponse) {
        Glide.with(this).load(image.url).placeholder(R.drawable.film).into(dateImageView)
        pictureTitle.text = image.title.toString()
        pictureDate.text = image.date.toString()
        pictureDescription.text=image.explanation.toString()
    }
}