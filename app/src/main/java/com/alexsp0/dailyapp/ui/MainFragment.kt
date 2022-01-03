package com.alexsp0.dailyapp.ui

import android.content.Intent
import android.net.Uri
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainFragment(presenter:MainPresenterImpl) : Fragment() {
    private var presenter : MainPresenterImpl = presenter
    private lateinit var image : ImageView
    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetHeader : TextView
    private lateinit var bottomSheetDescription : TextView
    private lateinit var input_wiki_text_layout : TextInputLayout
    private lateinit var input_text_search_wiki : TextInputEditText
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
        initSearchWikiIcon(view)
        presenter.getDailyImage()
        return view
    }

    private fun initSearchWikiIcon(view : View) {
        input_wiki_text_layout = view.findViewById<TextInputLayout>(R.id.input_text_search_wiki_layout)
        input_text_search_wiki = view.findViewById<TextInputEditText>(R.id.input_edit_text_search_wiki)
        input_wiki_text_layout.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://en.wikipedia.org/wiki/${input_text_search_wiki.text.toString()}"
            val uri = Uri.parse(url)
            intent.data = uri
            startActivity(intent)
        }
    }

    fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun setImage(imageInfo : NasaImageResponse) {
        Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(image)
        bottomSheetHeader.text = imageInfo.title
        bottomSheetDescription.text = imageInfo.explanation
//        image.setImageResource(R.drawable.film)
//        bottomSheetHeader.text = "Header"
//        bottomSheetDescription.text = "Description"
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: MainPresenterImpl) = MainFragment(presenter)
    }
}