package com.alexsp0.dailyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alexsp0.dailyapp.R
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.presenters.MainPresenterImpl
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment(presenter:MainPresenterImpl) : Fragment() {
    private var presenter : MainPresenterImpl = presenter
    private lateinit var image : ImageView
    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetHeader : TextView
    private lateinit var bottomSheetDescription : TextView
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
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        presenter.getDailyImage()
        return view
    }

    fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun setImage(imageInfo : NasaImageResponse) {
        //Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(image)
        image.setImageResource(R.drawable.film)
        bottomSheetHeader.text = "Header"
        bottomSheetDescription.text = "Description"
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: MainPresenterImpl) = MainFragment(presenter)
    }
}